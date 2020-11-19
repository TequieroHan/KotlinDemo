package com.my_go.mvvmlibrary.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:51
 * Describe:
 */
class DoubleLiveData : MutableLiveData<Double>() {
    override fun getValue(): Double? {
        return super.getValue() ?: 0.0
    }
}