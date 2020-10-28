package com.my_go.mylibrary.manager

import android.app.Activity
import android.content.Intent
import android.os.Process
import java.util.*
import kotlin.system.exitProcess

/**
 * Create by Package com.my_go.mylibrary.manager
 * Created by 毛勇 on 2020/10/28
 * Current System Time 15:58
 * Describe: Activity管理类
 */
class AppManager {
    companion object {
        private var instance: AppManager? = null

        //活动栈
        private var activityStack: Stack<Activity>? = null

        @Synchronized
        fun getInstance(): AppManager? {
            if (null == instance) {
                instance = AppManager()
            }
            return instance
        }

        /**
         * 重启app
         */
        fun restartApp(activity: Activity?) {
            val rIntent = Intent(activity, getRestarttActivityClass(activity))
            rIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//Intent.FLAG_ACTIVITY_NEW_TASK
            when {
                null != rIntent.component -> {
                    rIntent.action = Intent.ACTION_MAIN
                    rIntent.addCategory(Intent.CATEGORY_LAUNCHER)
                }
            }
            activity?.finish()
            activity?.startActivity(rIntent)
            killCurrentProcess()
        }

        /**
         * 杀死当前进程
         */
        private fun killCurrentProcess() {
            Process.killProcess(Process.myPid())
            exitProcess(10)
        }

        /**
         * out Activity 相当于 ? extends  Activity 指定参数类型的 上限
         * in Activity 相当于 ? super Activity 指定参数类型的 下限
         */
        private fun getRestarttActivityClass(activity: Activity?): Class<out Activity>? {
            val pmIntent =
                activity?.packageManager?.getLaunchIntentForPackage(activity.packageName) as Intent
            when {
                (null != pmIntent && null != pmIntent.component) -> {
                    try {
                        return Class.forName(pmIntent.component.className) as Class<out Activity>?
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
            }
            return null
        }

    }

    /**
     * 添加一个活动到堆栈
     */
    fun addActivity(activity: Activity?) {
        when {
            null == activityStack -> {
                activityStack = Stack()
            }
        }
        activityStack?.add(activity)
    }

    /**
     * 获取当前Activity
     */
    fun currentActivity(): Activity {
        return activityStack?.lastElement() as Activity
    }

    /**
     * 结束当前活动页面
     */
    fun finishActivity() {
        val activity = activityStack?.lastElement() as Activity
        finishActivity(activity)
    }

    /**
     * 结束指定活动页面
     */
    fun finishActivity(activity: Activity?) {
        var ac = activity;
        when {
            null != ac -> {
                activityStack?.remove(ac)
                ac.finish()
                ac = null
            }
        }
    }

    /**
     * 结束指定类名的活动
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有活动
     */
    fun finishActivitys() {
        for (activity in activityStack!!) {
            when {
                null != activity -> {
                    activity.finish()
                }
            }
        }
        activityStack?.clear()
    }

    /**
     * 退出应用程序
     */
    fun appExit() {
        try {
            finishActivitys()
            //退出进程
            Process.killProcess(Process.myPid())
            exitProcess(1)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}