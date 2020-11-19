package com.my_go.mvvmlibrary.callback.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.callback.livedata
 * Created by 毛勇 on 2020/11/6
 * Current System Time 14:05
 * Describe:自定义的Boolean类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class BooleanLiveData
    : MutableLiveData<Boolean>() {
    override fun getValue(): Boolean? {
        return super.getValue() ?: false
    }
}