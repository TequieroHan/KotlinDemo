package com.my_go.mvvmlibrary.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.lang.Exception
import java.net.HttpURLConnection

/**
 * Create by Package com.my_go.mvvmlibrary.network
 * Created by 毛勇 on 2020/11/5
 * Current System Time 16:33
 * Describe:
 */
object NetworkUtil {

    var URL: String = "http://www.baidu.com"

    /**
     * NetworkAvailable
     */
    var NET_CNNT_BAIDU_OK = 1

    /**
     * no NetworkAvailable
     */
    var NET_CNNT_BAIDU_TIMEOUT = 2

    /**
     * Net no ready
     */
    var NET_NOT_PREPARE = 3

    /**
     * net error
     */
    var NET_ERROR = 4

    /**
     * TIMEOUT
     */
    private const val TIMEOUT = 3000

    /**
     * 检测网络是否可用
     */
    fun isNetworkAvailable(context: Context?): Boolean? {
        var manager = context?.applicationContext
            ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return when (manager) {
            null -> {
                false
            }
            else -> {
                var networkInfo = manager.activeNetworkInfo as NetworkInfo
                networkInfo != null && networkInfo.isAvailable
            }
        }
    }

    /**
     * 获取网络状态
     */
    fun getNetState(context: Context?): Int {
        try {
            var conManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            when {
                conManager != null -> {
                    var network = conManager.activeNetworkInfo as NetworkInfo
                    when {
                        network != null -> {
                            when {
                                network.isAvailable && network.isConnected -> {
                                    when {
                                        connectionNetwork() == false -> {
                                            return NET_CNNT_BAIDU_TIMEOUT
                                        }
                                        else -> {
                                            return NET_CNNT_BAIDU_OK
                                        }
                                    }
                                }
                                else -> {
                                    return NET_NOT_PREPARE
                                }
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return NET_ERROR
    }

    /**
     * 网络连接
     */
    private fun connectionNetwork(): Boolean? {
        var result: Boolean? = false
        var httpUrl: HttpURLConnection? = null
        try {
            httpUrl = java.net.URL(URL).openConnection() as HttpURLConnection
            httpUrl.connectTimeout = TIMEOUT
            httpUrl.connect()
            result = true
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            when {
                null != httpUrl -> {
                    httpUrl.disconnect()
                }
            }
            httpUrl = null
        }
        return result
    }

    /**
     * 判断是否是Wifi连接
     */
    fun isWifi(context: Context?): Boolean {
        var connManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var networkInfo = connManager.activeNetworkInfo as NetworkInfo
        return when {
            networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI -> {
                true
            }
            else -> {
                false
            }
        }
    }

}