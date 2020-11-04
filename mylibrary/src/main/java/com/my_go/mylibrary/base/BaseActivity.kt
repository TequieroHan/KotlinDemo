package com.my_go.mylibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.my_go.mylibrary.manager.AppManager
import org.greenrobot.eventbus.EventBus

/**
 * Create by Package com.my_go.mylibrary.base
 * Created by 毛勇 on 2020/10/26
 * Current System Time 21:26
 * Describe: T : BaseView 等同于 T extends BaseView
 */
open abstract class BaseActivity<T : BaseView, P : BasePresenter<T>> : AppCompatActivity(),
    BaseView {

    var presenter: P? = null // P层 自己在强转

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            initParam(savedInstanceState);
        } else if (intent != null && intent.extras != null) {
            initParam(intent.extras);
        }
        setContentView(bindLayout())
        AppManager.getInstance()?.addActivity(this)
        presenter = bindPresenter();
        when {
            null != presenter -> {
                presenter?.onAttach(this as T)
            }
        }
        initView();
        initData();
    }

    override fun onStart() {
        super.onStart()
    }

    //绑定P层
    abstract fun bindPresenter(): P?

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


    override fun onDestroy() {
        super.onDestroy()
        when {
            null != presenter -> {
                presenter?.onDetch()//解绑
                presenter = null
            }
        }
        AppManager.getInstance()?.finishActivity(this)
    }
}