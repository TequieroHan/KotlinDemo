package com.my_go.kotlin.app

import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import com.my_go.kotlin.app.weight.loadcallback.EmptyCallback
import com.my_go.kotlin.app.weight.loadcallback.ErrorCallback
import com.my_go.kotlin.app.weight.loadcallback.LoadingCallback
import com.my_go.mvvmlibrary.base.BaseApp


/**
 * Create by Package com.my_go.kotlin.app
 * Created by 毛勇 on 2020/11/18
 * Current System Time 21:18
 * Describe:
 */
class MyApp : BaseApp() {

    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback())//添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .setDefaultCallback(SuccessCallback::class.java)
            .commit()
    }
}