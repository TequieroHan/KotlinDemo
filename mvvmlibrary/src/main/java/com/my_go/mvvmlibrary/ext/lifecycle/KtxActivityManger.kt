package com.my_go.mvvmlibrary.ext.lifecycle

import android.app.Activity
import java.util.*

/**
 * Create by Package com.my_go.mvvmlibrary.ext.lifecycle
 * Created by 毛勇 on 2020/11/11
 * Current System Time 15:43
 * Describe: 活动管理者
 */
object KtxActivityManger {
    private val mActivityList = LinkedList<Activity>()
    val currentActivity: Activity?
        get() {
            if (mActivityList.isEmpty()) {
                return null
            }
            return mActivityList.last
        }

    /**
     * 活动 入栈
     */
    fun pushActivity(activity: Activity) {
        when {
            mActivityList.contains(activity) -> {
                when {
                    mActivityList.last != activity -> {
                        mActivityList.remove(activity)
                        mActivityList.add(activity)
                    }
                }
            }
            else -> {
                mActivityList.add(activity)
            }
        }
    }

    /***
     * activity 出栈
     */
    fun removeActivity(activity: Activity) {
        mActivityList.remove(activity)
    }

    /**
     * 关闭当前活动
     */
    fun finishCurrentActivity() {
        currentActivity?.finish()
    }

    /***
     * 关闭传入的活动
     * @param activity
     */
    fun finishActivity(activity: Activity) {
        mActivityList.remove(activity)
        activity.finish()
    }

    /***
     * 关闭传入活动类名
     * @param clazz 关闭活动类名
     */
    fun finishClassActivity(clazz: Class<*>) {
        for (activity in mActivityList) {
            when (activity.javaClass) {
                clazz -> {
                    activity.finish()
                }
            }
        }
    }

    /**
     * 关闭所有活动页面
     */
    fun finishAllActivity() {
        for (activity in mActivityList) {
            activity.finish()
        }
    }
}