package com.my_go.mvvmlibrary.callback.databind

import androidx.databinding.ObservableField

/**
 * Create by Package com.my_go.mvvmlibrary.callback.databind
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:36
 * Describe:
 */

class DoubleObservableField(value: Double = 0.0) : ObservableField<Double>(value) {
    override fun get(): Double? {
        return super.get()!!
    }
}