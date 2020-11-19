package com.my_go.mvvmlibrary.callback.livedata.event

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata.event
 * Created by 毛勇 on 2020/11/6
 * Current System Time 15:19
 * Describe:可避免共享作用域 VM 下 liveData 被 observe 时旧数据倒灌的情况
 */
class UnPeekLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        hook(observer)
    }

    private fun hook(observer: Observer<in T>) {
        val liveDataClass = LiveData::class.java
        try {
            val mObservers = liveDataClass.getDeclaredField("mObservers")
            mObservers.isAccessible = true
            val observers = mObservers[this]
            val observerClass: Class<*> = observer.javaClass
            val getMethod = observerClass.getDeclaredMethod("get", Any::class.java)
            getMethod.isAccessible = true
            //获取到observer在集合中对应的ObserverWrapper对象
            val objectWrapperEntry = getMethod.invoke(observers, observer)
            var objectWrapper: Any? = null
            if (objectWrapperEntry is Map.Entry<*, *>) {
                objectWrapper = objectWrapperEntry.value
            }
            if (objectWrapper == null) {
                throw NullPointerException("ObserverWrapper can not be null")
            }
            //获取ObserverWrapper的Class对象  LifecycleBoundObserver extends ObserverWrapper
            val wrapperClass: Class<*>? = objectWrapper.javaClass.superclass
            //获取ObserverWrapper的field mLastVersion
            val mLastVersion = wrapperClass!!.getDeclaredField("mLastVersion")
            mLastVersion.isAccessible = true
            //获取liveData的field mVersion
            val mVersion = liveDataClass.getDeclaredField("mVersion")
            mVersion.isAccessible = true
            val mV = mVersion[this]
            mLastVersion[objectWrapper] = mV
            mObservers.isAccessible = false
            getMethod.isAccessible = false
            mLastVersion.isAccessible = false
            mVersion.isAccessible = false

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}