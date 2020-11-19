package com.my_go.mvvmlibrary.callback.databind

import androidx.databinding.ObservableField

/**
 * Create by Package com.my_go.mvvmlibrary.callback.databind
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:38
 * Describe:
 */
class IntObservableField(value: Int = 0) : ObservableField<Int>(value) {
    override fun get(): Int? {
        return super.get()!!
    }
}