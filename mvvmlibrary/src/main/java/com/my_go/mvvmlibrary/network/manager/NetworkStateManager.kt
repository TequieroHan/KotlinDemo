package com.my_go.mvvmlibrary.network.manager

import android.app.usage.NetworkStats
import android.net.Network
import com.my_go.mvvmlibrary.callback.livedata.event.UnPeekLiveData

/**
 * Create by Package com.my_go.mvvmlibrary.network.manager
 * Created by 毛勇 on 2020/11/6
 * Current System Time 14:01
 * Describe: 网络变化管理者
 */
class NetworkStateManager private constructor() {
    val mNetworkStateCallback = UnPeekLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }
}