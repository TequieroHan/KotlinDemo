package com.my_go.kotlin.ui.splash

import android.content.Intent
import android.os.Bundle
import com.my_go.kotlin.R
import com.my_go.kotlin.ui.home.HomeActivity
import com.my_go.mylibrary.base.BaseActivity
import com.my_go.mylibrary.base.BasePresenter
import com.my_go.mylibrary.base.BaseView
import com.my_go.mylibrary.utils.XStatusBar
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity<T : BaseView, P : BasePresenter<T>> : BaseActivity<T, P>() {


    override fun bindPresenter(): P? {
        return null;
    }

    override fun bindLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initParam(savedInstanceState: Bundle?) {
    }

    override fun initView() {
        XStatusBar.setTransparent(this)
        iv_splash.setImageDrawable(resources.getDrawable(R.color.colorAccent))
        iv_splash.postDelayed(Runnable {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    override fun initData() {
    }

}