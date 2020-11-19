package com.my_go.mvvmlibrary.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:44
 * Describe:
 */
class ByteLiveData : MutableLiveData<Byte>() {
    override fun getValue(): Byte? {
        return super.getValue() ?: 0
    }
}