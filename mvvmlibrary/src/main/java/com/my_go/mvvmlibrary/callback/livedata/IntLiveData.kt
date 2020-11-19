package com.my_go.mvvmlibrary.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:55
 * Describe:
 */
class IntLiveData : MutableLiveData<Int>() {

    override fun getValue(): Int? {
        return super.getValue() ?: 0
    }
}