package com.my_go.mylibrary.utils

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Process
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.my_go.mylibrary.R
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * Create by Package com.my_go.mylibrary.utils
 * Created by 毛勇 on 2020/10/27
 * Current System Time 15:32
 * Describe:跟App相关的辅助类
 * 1、获取应用程序名称
 * 2、获取应用程序版本名称信息
 * 3、获取版本号
 * 4、获取所有安装的应用程序,不包含系统应用
 * 5、获取应用程序的icon图标
 * 6、启动安装应用程序
 * 7、获取渠道名
 * 8、双击退出
 */
class AppUtils {

    companion object {
        var firstTime: Long = 0;

        /**
         * 获取应用程序名称
         * @return 应用名称
         */
        fun getAppName(context: Context): String? {
            try {
                val packageManager = context.packageManager as? PackageManager
                val pi = packageManager?.getPackageInfo(context.packageName, 0) as? PackageInfo
                var labelRes = pi?.applicationInfo?.labelRes as? Int
                return context.resources.getString(labelRes!!)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null;
        }

        /***
         * 获取应用程序版本名称信息
         * @return:当前应用的版本名称
         */
        fun getVersionName(context: Context): String? {
            try {
                val packageManager = context.packageManager as? PackageManager
                val pi = packageManager?.getPackageInfo(context.packageName, 0) as? PackageInfo
                return pi?.versionName
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        /**
         * 获取版本号
         *  @return 当前应用的版本号
         */
        fun getVersionCode(context: Context): Int? {
            try {
                val packageManager = context.packageManager as? PackageManager
                val pi = packageManager?.getPackageInfo(context.packageName, 0) as? PackageInfo
                return pi?.versionCode
            } catch (e: Exception) {
                e.printStackTrace()
                return 1
            }
        }

        /**
         * 获取应用程序的icon图标
         * @return 返回应用程序图标
         *  返回NULL时 则报错
         */
        fun getApplicationIcon(context: Context): Drawable? {
            try {
                val packageManager = context.packageManager as? PackageManager
                val pi = packageManager?.getPackageInfo(context.packageName, 0) as? PackageInfo
                return pi?.applicationInfo?.loadIcon(packageManager)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        /**
         * 获取所有安装的应用程序,不包含系统应用
         */
        fun getInstalledPackages(context: Context): List<PackageInfo> {
            val packageManager = context.packageManager as? PackageManager
            var packageInfos = packageManager?.getInstalledPackages(0) as? ArrayList<PackageInfo>
            val packageInfoList = ArrayList<PackageInfo>();
            if (packageInfos != null) {
                for (i in packageInfos.indices) {
                    if (packageInfos[i].applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                        packageInfoList.add(packageInfos[i])
                    }
                }
                return packageInfoList
            }
            return packageInfoList
        }

        /**
         * 双击退出
         */
        fun againstClick(activity: AppCompatActivity) {
            val secondTime: Long = System.currentTimeMillis()
            when {
                secondTime - firstTime > 2000 -> {
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.hint_exit_application),
                        Toast.LENGTH_LONG
                    ).show()
                    firstTime = secondTime
                }
                else -> {
                    activity.finish()
                }
            }
        }

        /**
         * 获取进程名称
         * @param pid 进程ID
         * @return 进程名称
         */
        fun getProcessName(pid: Int): String? {
            var reader: BufferedReader? = null
            try {
                val fr: FileReader = FileReader(
                    StringBuffer()
                        .append("/proc/")
                        .append(pid)
                        .append("/cmdline").toString()
                )
                reader = BufferedReader(fr)

            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            } finally {
                try {
                    reader?.close()
                } catch (exception: IOException) {
                    exception.printStackTrace()
                }
            }
            return null
        }

        /**
         * 判断是否在主线程
         */
        fun isMainProcess(context: Context): Boolean {
            val am =
                context.getSystemService(context.getString(R.string.n_activity)) as? ActivityManager
            var packageName: String = context.packageName as String
            var myPid: Int = Process.myPid()
            val processesInfo =
                am?.runningAppProcesses as? ArrayList<ActivityManager.RunningAppProcessInfo>
            when {
                null == processesInfo -> {
                    val runningServices =
                        am?.getRunningServices(2147483647) as? ArrayList<ActivityManager.RunningServiceInfo>
                    when {
                        null == runningServices -> {
                            return false
                        }
                        else -> {
                            val it = runningServices.iterator() as? Iterator<*>
                            var rsi: ActivityManager.RunningServiceInfo
                            do {
                                when {
                                    !it?.hasNext()!! -> {
                                        return false
                                    }
                                }
                                rsi = (it?.next() as? ActivityManager.RunningServiceInfo)!!
                            } while (rsi.pid != myPid || packageName != rsi.service.packageName)
                            return true
                        }
                    }
                }
                else -> {
                    val it = processesInfo.iterator() as? Iterator<*>
                    var rsi: ActivityManager.RunningServiceInfo
                    do {
                        when {
                            !it?.hasNext()!! -> return false
                        }
                        rsi = (it?.next() as? ActivityManager.RunningServiceInfo)!!
                    } while (rsi.pid != myPid || packageName != rsi.service.packageName)
                    return true
                }
            }
        }
    }
}