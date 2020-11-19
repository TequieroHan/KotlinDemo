package com.my_go.mvvmlibrary.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:53
 * Describe:
 */
class FloatLiveData : MutableLiveData<Float>() {
    override fun getValue(): Float? {
        return super.getValue() ?: 0f
    }
}