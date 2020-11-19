package com.my_go.mvvmlibrary.network.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.my_go.mvvmlibrary.network.NetworkUtil

/**
 * Create by Package com.my_go.mvvmlibrary.network.manager
 * Created by 毛勇 on 2020/11/6
 * Current System Time 11:55
 * Describe:网络变化转换器
 */
class NetworkStateReceive : BroadcastReceiver() {
    var isInit = true
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ConnectivityManager.EXTRA_NO_CONNECTIVITY -> {
                when (isInit) {
                    false -> {
                        when (NetworkUtil.isNetworkAvailable(context)) {
                            false -> {
                                //收到没有网络时判断之前的值是不是有网络，如果有网络才提示通知 ，防止重复通知
                                NetworkStateManager.instance.mNetworkStateCallback.value?.let {
                                    when {
                                        it.isSuccess -> {
                                            //没网
                                            NetworkStateManager.instance.mNetworkStateCallback.value =
                                                NetState(false)
                                        }
                                    }
                                    return
                                }
                                NetworkStateManager.instance.mNetworkStateCallback.value =
                                    NetState(false)
                            }
                            else -> {
                                //收到有网络时判断之前的值是不是没有网络，如果没有网络才提示通知 ，防止重复通知
                                NetworkStateManager.instance.mNetworkStateCallback.value?.let {
                                    when (it.isSuccess) {
                                        false -> {
                                            NetworkStateManager.instance.mNetworkStateCallback.value =
                                                NetState(true)
                                        }
                                    }
                                    return
                                }
                                NetworkStateManager.instance.mNetworkStateCallback.value =
                                    NetState(true)
                            }
                        }
                    }
                }
                this.isInit = false
            }
        }
    }
}