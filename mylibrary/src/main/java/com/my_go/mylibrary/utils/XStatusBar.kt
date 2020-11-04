package com.my_go.mylibrary.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.ColorInt


/**
 * Create by Package com.my_go.mylibrary.utils
 * Created by 毛勇 on 2020/11/3
 * Current System Time 15:08
 * Describe:状态栏工具类
 */
class XStatusBar {

    companion object {
        const val FAKE_STATUS_BAR_VIEW_ID = 110
        const val FAKE_TRANSLUCENT_VIEW_ID = 111
        const val DEFAULT_STATUS_BAR_ALPHA = 112

        /**
         * 设置状态栏颜色
         * @param color 状态栏颜色值
         */
        fun setColor(activity: Activity?, @ColorInt color: Int) {
            setColor(activity, color, DEFAULT_STATUS_BAR_ALPHA)
        }

        /**
         * @param statusBarAlpha 状态栏透明度
         */
        fun setColor(activity: Activity?, @ColorInt color: Int, statusBarAlpha: Int) {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                    activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    activity?.window?.statusBarColor = calcuateStatusColor(color, statusBarAlpha)
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                    activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    val viewGroup = activity?.window?.decorView as ViewGroup
                    var fakeStatusBarView = viewGroup.findViewById(FAKE_STATUS_BAR_VIEW_ID) as View
                    when (fakeStatusBarView) {
                        null -> {
                            viewGroup.addView(createStatusBarView(activity, color, statusBarAlpha))
                        }
                        else -> {
                            when (fakeStatusBarView.getVisibility()) {
                                View.GONE -> {
                                    fakeStatusBarView.visibility = View.VISIBLE
                                }
                                else -> {
                                    var tempColor = calcuateStatusColor(color,
                                        statusBarAlpha) as Int
                                    fakeStatusBarView.setBackgroundColor(tempColor)
                                }

                            }
                        }
                    }
                }
            }
        }

        /**
         * 生成一个和状态栏大小相同的彩色矩形条
         *
         * @param activity 需要设置的 activity
         * @param color    状态栏颜色值
         * @return 状态栏矩形条
         */
        fun createStatusBarView(activity: Activity?, @ColorInt color: Int): StatusBarView? {
            return createStatusBarView(activity, color, 0)
        }


        /**
         * @param activity 需要设置的 activity
         * @param color 状态栏颜色
         * @param statusBarAlpha 透明度
         * @return 状态栏矩形条
         */
        fun createStatusBarView(
            activity: Activity?,
            @ColorInt color: Int,
            statusBarAlpha: Int,
        ): StatusBarView? {

            val statusBarView = StatusBarView(activity)
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(activity)) as LinearLayout.LayoutParams
            statusBarView.layoutParams = params
            statusBarView.setBackgroundColor(calcuateStatusColor(color, statusBarAlpha))
            statusBarView.id = FAKE_STATUS_BAR_VIEW_ID
            return statusBarView
        }

        /**
         * 设置状态全透明
         */
        fun setTransparent(activity: Activity?) {
            when {
                Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT -> {
                    return
                }
            }
            transparentStatusBar(activity)
            setRootView(activity)
        }

        fun setRootView(activity: Activity?) {
            val parent = activity?.findViewById(android.R.id.content) as ViewGroup
            var i = 0
            val count = parent.childCount
            while (i < count) {
                val childView = parent.getChildAt(i)
                if (childView is ViewGroup) {
                    childView.setFitsSystemWindows(true)
                    (childView as ViewGroup).clipToPadding = true
                }
                i++
            }

        }

        /**
         * 状态透明
         */
        fun transparentStatusBar(activity: Activity?) {
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                    activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                    activity?.window?.statusBarColor = Color.TRANSPARENT
                }
                else -> {
                    activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                }
            }
        }


        /**
         * 获取状态栏高度
         */
        fun getStatusBarHeight(activity: Activity?): Int {
            var resource =
                activity?.resources?.getIdentifier("status_bar_height", "dimen", "android") as Int
            return activity?.resources.getDimensionPixelSize(resource)
        }


        /**
         * 计算状态栏颜色
         * @param color 颜色
         * @param alpha 透明度
         * @return 最终颜色
         */
        fun calcuateStatusColor(@ColorInt color: Int, alpha: Int): Int {
            return when (alpha) {
                0 -> {
                    color
                }
                else -> {
                    val a = 1 - alpha / 255f
                    var red = color shr 16 and 0xff
                    var green = color shr 8 and 0xff
                    var blue = color and 0xff
                    red = (red * a + 0.5).toInt()
                    green = (green * a + 0.5).toInt()
                    blue = (blue * a + 0.5).toInt()

                    0xff shl 24 or red shl 16 or green shl 8 or blue
                }
            }
        }

        class StatusBarView : View {
            constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
            constructor(context: Context?) : super(context) {}
        }
    }

}