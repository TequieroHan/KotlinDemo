package com.my_go.kotlin.app.event

import com.my_go.mvvmlibrary.base.viewmodel.BaseViewModel
import com.my_go.kotlin.data.model.bean.CollectBus
import com.my_go.mvvmlibrary.callback.livedata.event.EventLiveData

/**
 * Create by Package com.my_go.kotlin.app.event
 * Created by 毛勇 on 2020/12/1
 * Current System Time 11:52
 * Describe:
 */
class EventViewModel : BaseViewModel() {
    //全局收藏，在任意一个地方收藏或取消收藏，监听该值的界面都会收到消息
    val collectEvent = EventLiveData<CollectBus>()

    //分享文章通知
    val shareArticleEvent = EventLiveData<Boolean>()

    //添加TODO通知
    val todoEvent = EventLiveData<Boolean>()
}