package com.my_go.kotlin.utils.mvp.presenter

import com.my_go.kotlin.utils.mvp.views.IMainView
import com.my_go.mylibrary.base.BasePresenter

/**
 * Create by Package com.my_go.kotlin.utils.mvp.presenter
 * Created by 毛勇 on 2020/11/3
 * Current System Time 11:25
 * Describe:
 */
class MainPresenter(private var v: IMainView?) : BasePresenter<IMainView>() {

    init {
        initView()
        initMenu()
    }

    private fun initMenu() {
        v?.BottomMenu()
    }

    private fun initView() {
        v?.ViewPage()
    }
}