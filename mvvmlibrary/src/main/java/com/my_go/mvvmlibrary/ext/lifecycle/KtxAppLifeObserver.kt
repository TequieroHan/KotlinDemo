package com.my_go.mvvmlibrary.ext.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.my_go.mvvmlibrary.callback.livedata.BooleanLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.ext.lifecycle
 * Created by 毛勇 on 2020/11/11
 * Current System Time 17:11
 * Describe:
 */
object KtxAppLifeObserver : LifecycleObserver {

    var isForegroud = BooleanLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onForeground() {
        isForegroud.value = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onBackground() {
        isForegroud.value = false
    }
}