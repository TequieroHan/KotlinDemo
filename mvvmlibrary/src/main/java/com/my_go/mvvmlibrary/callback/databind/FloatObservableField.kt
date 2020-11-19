package com.my_go.mvvmlibrary.callback.databind

import androidx.databinding.ObservableField

/**
 * Create by Package com.my_go.mvvmlibrary.callback.databind
 * Created by 毛勇 on 2020/11/18
 * Current System Time 10:58
 * Describe:
 */
class FloatObservableField(value: Float = 0f) : ObservableField<Float>(value) {
    override fun get(): Float? {
        return super.get()!!
    }
}