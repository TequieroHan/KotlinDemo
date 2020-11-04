package com.my_go.mylibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus

/**
 * Create by Package com.my_go.mylibrary.base
 * Created by 毛勇 on 2020/10/29
 * Current System Time 10:19
 * Describe:
 */
abstract class BaseFragment<T : BaseView, P : BasePresenter<T>> : Fragment(), BaseView {
    var mView: View? = null
    var basePresenter: P? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        when (mView) {
            null -> {
                mView = inflater.inflate(bindLayout(), container, false)
                basePresenter = bindPresenter()
                when {
                    null != basePresenter -> {
                        basePresenter?.onAttach(this as T)
                    }
                }
                initView()
                initData()
            }
        }
        return mView
    }

    override fun onStart() {
        super.onStart()
    }


    //绑定P层
    abstract fun bindPresenter(): P?

    //绑定布局
    abstract fun bindLayout(): Int

    //初始化视图
    abstract fun initView()

    //初始化数据
    abstract fun initData()


    override fun onDestroy() {
        super.onDestroy()
        when {
            null != basePresenter -> {
                basePresenter?.onDetch()
            }
        }
    }
}