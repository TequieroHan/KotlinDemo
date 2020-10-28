package com.my_go.mylibrary.base

import android.app.Application
import com.my_go.mylibrary.utils.crash.CrashHandler

/**
 * Create by Package com.my_go.mylibrary.base
 * Created by 毛勇 on 2020/10/27
 * Current System Time 11:18
 * Describe:
 */
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CrashHandler.getIns(this)
    }
}