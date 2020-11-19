package com.my_go.mvvmlibrary.callback.databind

import androidx.databinding.ObservableField

/**
 * Create by Package com.my_go.mvvmlibrary.callback.databind
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:29
 * Describe:
 */
class ByteObservableField(value: Byte = 0) : ObservableField<Byte>(value) {
    override fun get(): Byte? {
        return super.get()!!
    }
}