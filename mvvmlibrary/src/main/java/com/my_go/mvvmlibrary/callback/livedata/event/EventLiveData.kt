package com.my_go.mvvmlibrary.callback.livedata.event

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.*

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata.event
 * Created by 毛勇 on 2020/11/6
 * Current System Time 14:12
 * Describe:
 * TODO://1.一条消息能被多个观察者消费
 * TODO://2.延迟期结束，不再能够收到旧消息的推送
 * TODO://3.并且旧消息在延迟期结束时能从内存中释放，避免内存溢出等问题
 * TODO://4.让非入侵设计成为可能，遵循开闭原则.用于限制从 Activity/Fragment 推送数据，推送数据务必通过唯一可信源来分发，
 */

class EventLiveData<T> : MutableLiveData<T>() {
    private var isCleaning = false //是否清除
    private var hasHandled = true//是否处理
    private var isDelaying = false//是否延迟
    var DELAY_TO_CLEAR_EVENT = 1000 //延迟清除
    private val mTimer = Timer()
    private var mTimerTask: TimerTask? = null
    var isAllowNullValue = false//是否允许为NULL值
    var isAllowToClear = false //是否允许清除数据

    /**
     * TODO://零侵入延时自动清除消息
     */
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer {
            when (isCleaning) {
                true -> {
                    hasHandled = true
                    isDelaying = false
                    isCleaning = false
                    return@Observer
                }
                else -> {
                    when {
                        !hasHandled -> {
                            hasHandled = true
                            isDelaying = true
                            observer.onChanged(it)
                        }
                        isDelaying -> {
                            observer.onChanged(it)
                        }
                    }
                }
            }
        })
    }

    /**
     * UnPeekLiveData 主要用于表现层的 页面转场 和 页面间通信 场景下的非粘性消息分发，
     * 出于生命周期安全等因素的考虑，不建议使用 observeForever 方法，
     *
     *
     * 对于数据层的工作，如有需要，可结合实际场景使用 MutableLiveData 或 kotlin flow。
     *
     * @param observer
     */
    override fun observeForever(observer: Observer<in T?>) {
        throw IllegalArgumentException("Do not use observeForever for communication between pages to avoid lifecycle security issues")
    }

    /**
     * 重写的 setValue 方法，默认不接收 null
     * 可通过 Builder 配置允许接收
     * 可通过 Builder 配置消息延时清理的时间
     * TODO:延时自动清理数据
     * @param value
     */
    override fun setValue(value: T?) {
        if (!isCleaning && !isAllowNullValue && value == null) {
            return
        }
        hasHandled = false
        isDelaying = false
        super.setValue(value)
        if (mTimerTask != null) {
            mTimerTask!!.cancel()
            mTimer.purge()//移除所有已取消的任务
        }
        if (value != null) {
            mTimerTask = object : TimerTask() {
                override fun run() {
                    clear()
                }
            }
            mTimer.schedule(mTimerTask, DELAY_TO_CLEAR_EVENT.toLong())
        }
    }

    private fun clear() {
        when (isAllowToClear) {
            true -> {
                isCleaning = true
                super.postValue(null)
            }
            else -> {
                isDelaying = true
                hasHandled = true
            }
        }
    }


}