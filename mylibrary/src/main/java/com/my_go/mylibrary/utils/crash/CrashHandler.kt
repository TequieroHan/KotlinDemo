package com.my_go.mylibrary.utils.crash

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.my_go.mylibrary.BuildConfig
import com.my_go.mylibrary.R
import com.my_go.mylibrary.manager.AppManager
import com.my_go.mylibrary.utils.AppUtils
import com.my_go.mylibrary.utils.SPUtils
import java.io.PrintWriter
import java.io.StringWriter

/**
 * Create by Package com.my_go.mylibrary.utils.crash
 * Created by 毛勇 on 2020/10/27
 * Current System Time 11:22
 * Describe:全局异常捕获类
 */
class CrashHandler : Thread.UncaughtExceptionHandler {

    private var mContext: Context? = null;
    private var bugInfomation: StringBuffer? = null;

    /**
     * 系统默认UncaughtExceptionHandler
     */
    private var mDefaultHandler: Thread.UncaughtExceptionHandler? = null;

    private constructor(context: Context) {
        init(context)
    }

    fun init(context: Context) {
        mContext = context
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为默认系统
        Thread.setDefaultUncaughtExceptionHandler(this)
    }


    companion object {
        private const val MAX_STACK_TRACE_SIZE = 131071 //128 KB - 1
        private var instances: CrashHandler? = null
        const val CRASH_REPORTER_EXTENSION: String = ".cr"
        const val SP_BUG = "BUG"

        @Synchronized
        fun getIns(context: Context): CrashHandler {
            if (instances == null) {
                instances = CrashHandler(context);
            }
            return instances!!
        }
    }

    /**
     * 回调函数
     */
    override fun uncaughtException(t: Thread?, e: Throwable?) {
        //收集设备参数信息
        collectDeviceInfo(this.mContext)
        //保存错误日志
        saveCrashInfo2File(e)
        mDefaultHandler?.uncaughtException(t, e)
    }

    /**
     * 保存错误日志并处理
     */
    private fun saveCrashInfo2File(e: Throwable?) {
        val write: StringWriter = StringWriter()
        val pWrite: PrintWriter = PrintWriter(write)
        e?.printStackTrace(pWrite)
        pWrite.close()
        var writeStr: String = write.toString();
        when {
            writeStr.length > MAX_STACK_TRACE_SIZE -> {
                val disclaimer: String = " [stack trace too large]"
                writeStr =
                    writeStr.substring(0, MAX_STACK_TRACE_SIZE - disclaimer.length) + disclaimer;
            }
        }
        bugInfomation?.append("DEBUG")?.append(writeStr)
        when {
            BuildConfig.DEBUG -> {
                //跳转页面
            }
            else -> {
                //发送到服务器
                sendCrashReportsToServer(mContext)
            }
        }
    }

    /**
     * 发送错误报告给服务器
     */
    private fun sendCrashReportsToServer(context: Context?) {
        val infomation = SPUtils.get(context, SP_BUG, "") as String
        if (infomation.length > 0) {
            SPUtils.put(context, SP_BUG, infomation)
        }
        //尚未做服务器上传
        //退出应用程序
        AppManager.getInstance()?.appExit()
    }

    /***
     * 收集设备参数信息
     */
    private fun collectDeviceInfo(ctx: Context?) {
        bugInfomation = StringBuffer()
        try {
            val pm = ctx?.packageManager as? PackageManager
            val pi =
                pm?.getPackageInfo(ctx.packageName, PackageManager.GET_ACTIVITIES) as? PackageInfo
            if (pi != null) {
                var versionName: String = "";
                var versionCode: String = "";
                if (pi.versionName != null) {
                    versionName = pi.versionName
                } else {
                    versionName = "null"
                }
                versionCode = pi.versionCode.toString();
                bugInfomation!!.append(ctx.getString(R.string.n_app_name))
                    .append(AppUtils.getAppName(ctx)).append("\n")
                bugInfomation!!.append(ctx.getString(R.string.n_app_version)).append(versionName)
                    .append("\n")
                bugInfomation!!.append(ctx.getString(R.string.n_app_version_code))
                    .append(versionCode).append("\n")
                bugInfomation!!.append(ctx.getString(R.string.n_model)).append(Build.MODEL)
                    .append("\n")
                bugInfomation!!.append(ctx.getString(R.string.n_device)).append(Build.DEVICE)
                    .append("\n")
                bugInfomation!!.append(ctx.getString(R.string.n_android_version))
                    .append(Build.VERSION.RELEASE).append("\n")
                bugInfomation!!.append(ctx.getString(R.string.n_manufacturer))
                    .append(Build.MANUFACTURER).append("\n")
                bugInfomation!!.append(ctx.getString(R.string.n_version_sdk))
                    .append(Build.VERSION.SDK_INT).append("\n")
                bugInfomation!!.append(ctx.getString(R.string.n_hardware)).append(Build.HARDWARE)
                    .append("\n")
                bugInfomation!!.append("--------------------------------------------------------")
                    .append("\n")
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
