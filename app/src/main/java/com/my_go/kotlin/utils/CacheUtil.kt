package com.my_go.kotlin.utils

import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.my_go.kotlin.data.model.bean.UserInfo
import com.tencent.mmkv.MMKV

/**
 * Create by Package com.my_go.kotlin.utils
 * Created by 毛勇 on 2020/11/24
 * Current System Time 13:38
 * Describe:缓存工具类
 */
object CacheUtil {

    /**
     *获取保存的用户信息
     */
    fun getUser(): UserInfo? {
        val kvApp = MMKV.mmkvWithID("app")
        val userCache = kvApp.decodeString("user")
        return if (TextUtils.isEmpty(userCache)) {
            null
        } else {
            Gson().fromJson(userCache, UserInfo::class.java)
        }
    }

    /**
     * 设置账户并设置登录
     */
    fun setUser(userInfo: UserInfo?) {
        val mmkv = MMKV.mmkvWithID("app")
        if (userInfo == null) {
            mmkv.encode("user", "")
            setIsLogin(false)
        } else {
            mmkv.encode("user", Gson().toJson(userInfo))
            setIsLogin(true)
        }
    }

    /**
     * 设置是否已登录
     */
    fun setIsLogin(isLogin: Boolean) {
        val mk = MMKV.mmkvWithID("app")
        mk.encode("login", isLogin)
    }

    /**
     * 判断是否登录
     */
    fun isLogin(): Boolean {
        val mk = MMKV.mmkvWithID("app")
        return mk.decodeBool("login", false)
    }

    /**
     *判断是否是第一次登录
     */
    fun isFirst(first: Boolean): Boolean {
        val mk = MMKV.mmkvWithID("app")
        return mk.decodeBool("first", first)
    }

    /***
     *是否第一次登录
     */
    fun isFirst(): Boolean {
        val mk = MMKV.mmkvWithID("app")
        return mk.decodeBool("first", true)
    }

    /**
     * 首页是否开启获取指定文章
     */
    fun isNeedTop(): Boolean {
        val mmkv = MMKV.mmkvWithID("app")
        return mmkv.decodeBool("top", true)
    }

    /***'
     *设置首页是否开启获取指定文章
     */
    fun isNeedTop(isTop: Boolean): Boolean {
        val mmkv = MMKV.mmkvWithID("app")
        return mmkv.decodeBool("top", isTop)
    }

    /***
     * 获取搜索历史数据
     */
    fun getSearchHistoryData(): ArrayList<String> {
        val mmkv = MMKV.mmkvWithID("cache")
        val searchCacheStr = mmkv.decodeString("history")
        if (TextUtils.isEmpty(searchCacheStr)) {
            return Gson().fromJson(searchCacheStr, object : TypeToken<ArrayList<String>>() {}.type)
        }
        return arrayListOf()
    }

    /***
     *添加搜索历史数据
     */
    fun setSearchHistoryData(searchHistoryData: String) {
        val mmkv = MMKV.mmkvWithID("cache")
        mmkv.encode("history", searchHistoryData)
    }

}