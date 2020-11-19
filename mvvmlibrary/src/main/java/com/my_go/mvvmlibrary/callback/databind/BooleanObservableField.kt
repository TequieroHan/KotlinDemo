package com.my_go.mvvmlibrary.callback.databind

import androidx.databinding.ObservableField

/**
 * Create by Package com.my_go.mvvmlibrary.callback.databind
 * Created by 毛勇 on 2020/11/18
 * Current System Time 10:49
 * Describe: ObservableField 需要在 build.gradle 中配置   dataBinding{enabled = true}
 */

class BooleanObservableField(value: Boolean = false) : ObservableField<Boolean>(value) {
    override fun get(): Boolean? {
        return super.get()!!
    }
}