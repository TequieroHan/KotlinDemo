package com.my_go.mvvmlibrary.base

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.content.IntentFilter
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import androidx.lifecycle.ProcessLifecycleOwner
import com.my_go.mvvmlibrary.ext.lifecycle.KtxAppLifeObserver
import com.my_go.mvvmlibrary.ext.lifecycle.KtxLifeCycleCallBack
import com.my_go.mvvmlibrary.network.manager.NetworkStateManager
import com.my_go.mvvmlibrary.network.manager.NetworkStateReceive

/**
 * Create by Package com.my_go.mvvmlibrary.base.viewmodel
 * Created by 毛勇 on 2020/11/5
 * Current System Time 14:13
 * Describe:
 * lazy:就是延迟初始化。
 * lateinit:是后期初始化。
 * lazy只能用于val属性，而lateinit只能应用于vars，因为它不能编译成final字段，因此不能保证不变性。
 */
val application: Application by lazy { Ktx.app }

class Ktx : ContentProvider() {

    companion object {
        lateinit var app: Application
        private var mNetworkStateReceive: NetworkStateReceive? = null
        var watchActivityLife = true
        var watchAppLife = true
    }

    override fun onCreate(): Boolean {
        var application = context!!.applicationContext as Application
        install(application)
        return true
    }
    private fun install(application: Application) {
        app = application
        mNetworkStateReceive = NetworkStateReceive()
        app.registerReceiver(
            mNetworkStateReceive, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        if (watchActivityLife) {
            application.registerActivityLifecycleCallbacks(KtxLifeCycleCallBack())
        }
        if (watchAppLife){
            ProcessLifecycleOwner.get().lifecycle.addObserver(KtxAppLifeObserver)
        }
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }


}