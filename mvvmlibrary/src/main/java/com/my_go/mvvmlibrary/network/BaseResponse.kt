package com.my_go.mvvmlibrary.network

/**
 * Create by Package com.my_go.mvvmlibrary.network
 * Created by 毛勇 on 2020/11/18
 * Current System Time 20:14
 * Describe:
 */
abstract class BaseResponse<T> {
    //抽象方法，用户的基类继承该类时，需要重写该方法
    abstract fun isSuccess(): Boolean
    abstract fun getResponseData(): T
    abstract fun getResponseCode(): Int
    abstract fun getResponseMsg(): String
}