package com.my_go.kotlin.home

import android.os.Bundle
import com.my_go.kotlin.R
import com.my_go.kotlin.base.MyBaseActivity

/**
 * Create by Package com.my_go.kotlin.home
 * Created by 毛勇 on 2020/10/26
 * Current System Time 20:34
 * Describe:
 */
class HomeActivity : MyBaseActivity() {
    override fun bindLayout(): Int {
        return R.layout.activity_main;
    }

    /**
     * 异常：
     * kotlin.NotImplementedError: An operation is not implemented: Not yet implemented
     * 解决办法：
     * TODO("not implemented") 去掉这句话就好了
     */
    override fun initParam(savedInstanceState: Bundle?) {

    }

    override fun initView() {
    }

    override fun initData() {
    }

}