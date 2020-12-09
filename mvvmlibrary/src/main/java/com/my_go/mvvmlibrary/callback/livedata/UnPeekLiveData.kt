package com.my_go.mvvmlibrary.callback.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata
 * Created by 毛勇 on 2020/11/21
 * Current System Time 0:08
 * Describe:可避免共享作用域 VM 下 liveData 被 observe 时旧数据倒灌的情况
 */
class UnPeekLiveData<T> : MutableLiveData<T>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        hook(observer)
    }

    private fun hook(observer: Observer<in T>) {
        val liveDateClazz = LiveData::class.java
        try {
            val mObservers = liveDateClazz.getDeclaredField("mObservers")
            mObservers.isAccessible = true
            val observers = mObservers[this]
            val observerClass: Class<*> = observers.javaClass
            val methodGet = observerClass.getDeclaredMethod("get", Any::class.java)
            methodGet.isAccessible = true
            val objectWrapperEntry = methodGet.invoke(observers, observer)
            var objectWarning: Any? = null
            if (objectWrapperEntry is Map.Entry<*, *>) {
                objectWarning = objectWrapperEntry.value
            }
            if (objectWarning == null) {
                throw NullPointerException("ObjectWarning 为 Null")
            }
            val superclass: Class<*>? = objectWarning.javaClass.superclass
            val mLastVersion = superclass!!.getDeclaredField("mLastVersion")
            mLastVersion.isAccessible = true

            val mVersion = liveDateClazz.getDeclaredField("mVersion")
            mVersion.isAccessible = true
            val mV = mVersion[this]
            mLastVersion[objectWarning] = mV

            mObservers.isAccessible = false
            methodGet.isAccessible = false
            mLastVersion.isAccessible = false
            mVersion.isAccessible = false

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}