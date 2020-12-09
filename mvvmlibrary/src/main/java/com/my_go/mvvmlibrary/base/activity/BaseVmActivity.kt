package com.my_go.mvvmlibrary.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel
import com.my_go.mvvmlibrary.ext.getVmClazz
import com.my_go.mvvmlibrary.network.manager.NetState
import com.my_go.mvvmlibrary.network.manager.NetworkStateManager

/**
 * Create by Package com.my_go.mvvmlibrary.base.viewmodel
 * Created by 毛勇 on 2020/11/18
 * Current System Time 13:34
 * Describe:
 */

abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {

    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isUserDb = false

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUserDb) {
            setContentView(layoutId())
        } else {
            initDataBind()
        }
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)//初始化视图方法
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observe(
            this,
            Observer { onNetworkStateChanged(it) })
    }

    /**
     * 监听网络状态
     */
    open fun onNetworkStateChanged(netState: NetState) {

    }


    /**
     * 创建LevelData数据观察者
     */
    abstract fun createObserver()

    /**
     * 注册Ui事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.loadingChange.showLoading.observe(this, Observer {
            showLoading(it)
        })
        //关闭弹窗
        mViewModel.loadingChange.dismissLoading.observe(this, Observer { dismissLoading() })
    }

    /**
     * 创建ViewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 是否需要使用DataBinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    fun userDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }

    /***
     *初始化Databinding操作
     */
    open fun initDataBind() {

    }

    protected fun addLoadingObserve(vararg viewModels: BaseViewModel) {
        viewModels.forEach { viewModel ->
            viewModel.loadingChange.showLoading.observe(this, Observer { showLoading(it) })
            viewModel.loadingChange.dismissLoading.observe(this, Observer { dismissLoading() })
        }
    }

}