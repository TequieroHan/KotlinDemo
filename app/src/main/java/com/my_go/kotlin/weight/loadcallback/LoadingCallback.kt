package com.my_go.kotlin.weight.loadcallback

import com.kingja.loadsir.callback.Callback
import com.my_go.kotlin.R


/**
 * Create by Package com.my_go.kotlin.weight.loadcallback
 * Created by 毛勇 on 2020/11/19
 * Current System Time 10:20
 * Describe:
 */
class LoadingCallback :Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }
}