package com.my_go.mvvmlibrary.callback.databind

import androidx.databinding.ObservableField

/**
 * Create by Package com.my_go.mvvmlibrary.callback.databind
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:39
 * Describe:
 */
class ShortObservableField(value: Short = 0) : ObservableField<Short>(value) {
    override fun get(): Short? {
        return super.get()!!
    }
}