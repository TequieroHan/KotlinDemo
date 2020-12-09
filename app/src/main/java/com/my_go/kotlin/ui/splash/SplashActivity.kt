package com.my_go.kotlin.ui.splash

import android.os.Bundle
import com.my_go.kotlin.R
import com.my_go.kotlin.app.base.BaseActivity
import com.my_go.kotlin.databinding.ActivitySplashBinding
import com.my_go.kotlin.utils.SettingUtil
import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * Create by Package com.my_go.kotlin.ui.splash
 * Created by 毛勇 on 2020/12/3
 * Current System Time 15:34
 * Describe:
 */
class SplashActivity : BaseActivity<BaseViewModel, ActivitySplashBinding>() {
    override fun layoutId(): Int = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {
        splash.setBackgroundColor(SettingUtil.getColor(this))
    }
}