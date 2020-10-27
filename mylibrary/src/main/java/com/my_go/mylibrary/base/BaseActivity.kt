package com.my_go.mylibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Package com.my_go.mylibrary.base
 * Created by 毛勇 on 2020/10/26
 * Current System Time 21:26
 * Describe:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else if (intent != null && intent.extras != null) {
            initParam(intent.extras);
        }
        setContentView(bindLayout())
        initView();
        initData();
    }

    abstract fun bindLayout(): Int;

    /***
     * 界面跳转传递参数
     */
    abstract fun initParam(savedInstanceState: Bundle?);

    /**
     * 初始化视图方法
     */
    abstract fun initView();

    /**
     * 初始化数据
     */
    abstract fun initData();

}