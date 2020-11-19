package com.my_go.mvvmlibrary.ext.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.my_go.mvvmlibrary.ext.utils.logd

/**
 * Create by Package com.my_go.mvvmlibrary.ext.lifecycle
 * Created by 毛勇 on 2020/11/11
 * Current System Time 15:41
 * Describe:
 */

class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxActivityManger.pushActivity(activity)
        "onActivityCreated:${activity.localClassName}".logd()
    }

    override fun onActivityStarted(activity: Activity) {
        "onActivityStarted:${activity.localClassName}".logd()
    }

    override fun onActivityResumed(activity: Activity) {
        "onActivityResumed:${activity.localClassName}".logd()
    }

    override fun onActivityPaused(activity: Activity) {
        "onActivityPaused:${activity.localClassName}".logd()
    }

    override fun onActivityStopped(activity: Activity) {
        "onActivityStopped:${activity.localClassName}".logd()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        "onActivityDestroyed:${activity.localClassName}".logd()
        KtxActivityManger.removeActivity(activity)
    }

}