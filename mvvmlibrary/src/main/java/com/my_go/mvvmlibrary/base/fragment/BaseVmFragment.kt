package com.my_go.mvvmlibrary.base.fragment

import android.content.Context
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel
import com.my_go.mvvmlibrary.ext.getVmClazz
import com.my_go.mvvmlibrary.network.manager.NetState
import com.my_go.mvvmlibrary.network.manager.NetworkStateManager

/**
 * Create by Package com.my_go.mvvmlibrary.base.fragment
 * Created by 毛勇 on 2020/11/18
 * Current System Time 15:27
 * Describe:
 * TODO:1.lateinit 延迟初始化
 */
abstract class BaseVmFragment<VM : BaseViewModel> : Fragment() {
    //判断是否第一次加载
    private var isFirst: Boolean = true

    lateinit var mViewModel: VM

    lateinit var mActivity: AppCompatActivity

    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        initView(savedInstanceState)
        createObserver()
        registerDefaUiChange()
        initData()
    }

    /**
     *Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    /**
     * 监听网络状态
     */
    open fun onNetworkStateChanged(netState: NetState) {
    }

    /**
     * 注册UI事件
     */
    private fun registerDefaUiChange() {
        mViewModel.loadingChange.showLoading.observe(viewLifecycleOwner, Observer {
            showLoading(
                if (it.isEmpty()) {
                    "请求网络中..."
                } else it
            )
        })
        mViewModel.loadingChange.dismissLoading.observe(viewLifecycleOwner, Observer {
            dismissLoading()
        })
    }

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    /**
     * 创建观察者
     */
    abstract fun createObserver()

    /**
     * 初始化View
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 创建ViewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     *是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            //延迟加载0.12秒加载 避免fragment跳转动画和渲染ui同时进行，出现些微的小卡顿
            view?.postDelayed({
                lazyLoadData()
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkStateManager.instance.mNetworkStateCallback.observe(
                    viewLifecycleOwner,
                    Observer {
                        //不是首次订阅时调用方法，防止数据第一次监听错误
                        if (!isFirst) {
                            onNetworkStateChanged(it)
                        }
                    }
                )
                isFirst = false
            }, 1200)
        }
    }

    /**
     * TODO：vararg 关键字，参数长度可变化
     */
    fun addLoadingObserver(vararg viewModels: BaseViewModel) {
        viewModels.forEach { vmodel ->
            //显示弹窗
            vmodel.loadingChange.showLoading.observe(viewLifecycleOwner, Observer {
                showLoading(it)
            })
            //隐藏弹窗
            vmodel.loadingChange.dismissLoading.observe(viewLifecycleOwner, Observer {
                dismissLoading()
            })
        }
    }
}