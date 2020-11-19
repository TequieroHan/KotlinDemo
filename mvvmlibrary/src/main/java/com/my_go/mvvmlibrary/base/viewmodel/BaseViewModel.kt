package com.my_go.mvvmlibrary.base.viewmodel

import androidx.lifecycle.ViewModel
import com.my_go.mvvmlibrary.callback.livedata.event.EventLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.base.viewmodel
 * Created by 毛勇 on 2020/11/5
 * Current System Time 11:53
 * Describe:ViewModel的基类 使用ViewModel类，放弃AndroidViewModel，原因：用处不大 完全有其他方式获取Application上下文
 */
open class BaseViewModel : ViewModel() {

    val loadingChange: UILoadingChange by lazy { UILoadingChange() }

    /**
     *inner 内部类
     * 可通知Activity/fragment 显示隐藏加载框 因为需要跟网络请求显示隐藏loading配套才加的
     */
    inner class UILoadingChange {
        //显示加载狂
        val showLoading by lazy { EventLiveData<String>() }

        //隐藏
        val dismissLoading by lazy { EventLiveData<Boolean>() }
    }
}