package com.my_go.kotlin.app.base

import android.content.res.Resources
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.my_go.kotlin.app.event.AppViewModel
import com.my_go.kotlin.app.event.EventViewModel
import com.my_go.kotlin.app.ext.dismissLoadingExt
import com.my_go.kotlin.app.ext.showLoadingExt
import com.my_go.mvvmlibrary.base.activity.BaseVmDbActivity
import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel
import com.my_go.mvvmlibrary.ext.getAppViewModel

/**
 * Create by Package com.my_go.kotlin.app.base
 * Created by 毛勇 on 2020/11/20
 * Current System Time 23:55
 * Describe:
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    //Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    //Application全局的ViewModel，用于发送全局通知操作
    val eventViewModel: EventViewModel by lazy { getAppViewModel<EventViewModel>() }

    abstract override fun layoutId(): Int

    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     * 创建liveData观察者
     */
    override fun createObserver() {}

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }
}