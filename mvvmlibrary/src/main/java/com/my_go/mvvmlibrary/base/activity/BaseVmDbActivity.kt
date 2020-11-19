package com.my_go.mvvmlibrary.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel

/**
 * Create by Package com.my_go.mvvmlibrary.base.viewmodel
 * Created by 毛勇 on 2020/11/18
 * Current System Time 15:17
 * Describe:
 * TODO:把ViewModel 和Databind注入进来了,需要使用Databind的清继承它
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {
    lateinit var mDatabind: DB;

    override fun onCreate(savedInstanceState: Bundle?) {
        userDataBind(true)
        super.onCreate(savedInstanceState)
    }

    override fun initDataBind() {
        mDatabind = DataBindingUtil.setContentView(this, layoutId())
        mDatabind.lifecycleOwner = this
    }
}