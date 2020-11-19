package com.my_go.mvvmlibrary.callback.databind

import androidx.databinding.ObservableField
import java.util.*

/**
 * Create by Package com.my_go.mvvmlibrary.callback.databind
 * Created by 毛勇 on 2020/11/18
 * Current System Time 11:42
 * Describe:
 */
class StringObservableField(value: String = "") : ObservableField<String>(value) {
    override fun get(): String? {
        return super.get()!!
    }
}