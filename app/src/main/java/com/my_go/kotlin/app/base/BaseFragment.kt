package com.my_go.kotlin.app.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.my_go.kotlin.app.event.AppViewModel
import com.my_go.kotlin.app.event.EventViewModel
import com.my_go.kotlin.app.ext.dismissLoadingExt
import com.my_go.kotlin.app.ext.hideSoftKeyboard
import com.my_go.kotlin.app.ext.showLoadingExt
import com.my_go.mvvmlibrary.base.fragment.BaseVmDbFragment
import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel
import com.my_go.mvvmlibrary.ext.getAppViewModel

/**
 * Create by Package com.my_go.kotlin.app.base
 * Created by 毛勇 on 2020/12/1
 * Current System Time 15:05
 * Describe:
 */
abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

    //Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
    val appViewModel: AppViewModel by lazy { getAppViewModel<AppViewModel>() }

    //Application全局的ViewModel，用于发送全局通知操作
    val eventViewModel: EventViewModel by lazy { getAppViewModel<EventViewModel>() }

    abstract override fun layoutId(): Int

    abstract override fun initView(savedInstanceState: Bundle?)

    /**
     *懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {
    }

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    override fun createObserver() {
    }

    /**
     *Fragment执行onViewCreated后触发
     */
    override fun initData() {
    }

    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    override fun dismissLoading() {
        dismissLoadingExt()
    }

    override fun onPause() {
        super.onPause()
        hideSoftKeyboard(activity)
    }

}