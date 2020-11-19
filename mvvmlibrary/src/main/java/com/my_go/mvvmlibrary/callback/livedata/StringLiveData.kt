package com.my_go.mvvmlibrary.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:56
 * Describe:
 */
class StringLiveData : MutableLiveData<String>() {

    override fun getValue(): String? {
        return super.getValue() ?: ""
    }
}