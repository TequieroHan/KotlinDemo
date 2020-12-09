package com.my_go.kotlin.app.weight.loadcallback

import com.kingja.loadsir.callback.Callback
import com.my_go.kotlin.R

/**
 * Create by Package com.my_go.kotlin.app.weight.loadcallback
 * Created by 毛勇 on 2020/11/20
 * Current System Time 23:44
 * Describe:
 */
class EmptyCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }
}