package com.my_go.kotlin.app.event

import android.graphics.Color
import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel
import com.my_go.mvvmlibrary.callback.livedata.UnPeekLiveData
import com.my_go.kotlin.data.model.bean.UserInfo
import com.my_go.kotlin.utils.CacheUtil
import com.my_go.kotlin.utils.SettingUtil
import com.my_go.kotlin.utils.SettingUtil.getColor
import com.my_go.mvvmlibrary.base.application
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * Create by Package com.my_go.kotlin.app.event
 * Created by 毛勇 on 2020/11/21
 * Current System Time 0:06
 * Describe:APP全局的ViewModel
 * ，可以存放公共数据，当他数据改变时，所有监听他的地方都会收到回调,也可以做发送消息
 */
class AppViewModel : BaseViewModel() {
    //用户信息
    val userInfo = UnPeekLiveData<UserInfo>()

    //app主题色
    val appColor = UnPeekLiveData<Int>()

    //app列标动画
    val appAnimation = UnPeekLiveData<Int>()

    init {
        //默认值保存账户信息，没有登录过则为NULL
        userInfo.value = CacheUtil.getUser()
        //初始化颜色
        val color: Int = getColor(application) as Int
        appColor.value = color
        //初始化列表动画
        appAnimation.value = SettingUtil.getListMode()

    }

}