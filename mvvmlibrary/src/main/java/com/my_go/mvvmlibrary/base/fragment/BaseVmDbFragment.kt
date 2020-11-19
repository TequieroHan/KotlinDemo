package com.my_go.mvvmlibrary.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel

/**
 * Create by Package com.my_go.mvvmlibrary.base.fragment
 * Created by 毛勇 on 2020/11/18
 * Current System Time 16:08
 * Describe:
 */

abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>() {

    lateinit var mDatabind: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDatabind = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mDatabind.lifecycleOwner = this
        return mDatabind.root
    }

}