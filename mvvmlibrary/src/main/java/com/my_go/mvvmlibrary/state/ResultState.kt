package com.my_go.mvvmlibrary.state

import androidx.lifecycle.MutableLiveData
import com.my_go.mvvmlibrary.network.AppException
import com.my_go.mvvmlibrary.network.BaseResponse
import com.my_go.mvvmlibrary.network.ExceptionHandle

/**
 * Create by Package com.my_go.mvvmlibrary.state
 * Created by 毛勇 on 2020/11/18
 * Current System Time 17:11
 * Describe:自定义结果集封装类
 * TODO:1.sealed class 为 密封类，密封类是不能实例化的
 * TODO:2.out 只将泛型类型作为函数的返回（输出）
 */
sealed class ResultState<out T> {
    companion object {
        fun <T> onAppSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onAppLoading(loadingMessage: String): ResultState<T> = Loading(loadingMessage)
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)
    }

    data class Loading(val loadingMessage: String) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: AppException) : ResultState<Nothing>()
}

/**
 *处理返回值
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: BaseResponse<T>) {
    value = if (result.isSuccess()) ResultState.onAppSuccess(result.getResponseData()) else
        ResultState.onAppError(AppException(result.getResponseCode(), result.getResponseMsg()))
}

/***
 *不处理返回值。直接返回请求结果
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: T) {
    value = ResultState.onAppSuccess(result)
}

/**
 *异常转换处理
 */
fun <T> MutableLiveData<ResultState<T>>.paresException(e: Throwable) {
    this.value = ResultState.onAppError(ExceptionHandle.handleException(e))
}