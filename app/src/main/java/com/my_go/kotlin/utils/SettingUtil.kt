package com.my_go.kotlin.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.blankj.utilcode.util.Utils
import com.my_go.kotlin.R
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.flow.*
import kotlin.math.roundToInt

/**
 * Create by Package com.my_go.kotlin.utils
 * Created by 毛勇 on 2020/11/24
 * Current System Time 14:09
 * Describe:设置工具类
 */
object SettingUtil {

    /**
     * 获取当前主题颜色
     */
    fun getColor(context: Context): Int {
        val defaultColor = ContextCompat.getColor(context, R.color.colorPrimary)
        val readToDataStore = DataStoreUtils.readToDataStore(
            context,
            defaultColor
        )
        var color: Int = 0
        if (readToDataStore != 0 && Color.alpha(readToDataStore) != 255) {
            color = defaultColor
        } else {
            color = readToDataStore
        }
        return color
    }

    /**
     *设置主题颜色
     */
    suspend fun setColor(context: Context, color: Int) {
        DataStoreUtils.writeToDataStore(context, color)
    }

    /**
     *获取列表动画模式
     */
    fun getListMode(): Int {
        val mmkv = MMKV.mmkvWithID("app")
        //0 关闭动画 1.渐显 2.缩放 3.从下到上 4.从左到右 5.从右到左
        return mmkv.decodeInt("mode", 2)
    }

    /***
     *设置列表动画模式
     *
     *@param mode 0 关闭动画
     *            1.渐显
     *            2.缩放
     *            3.从下到上
     *            4.从左到右
     *            5.从右到左
     */
    fun setListMode(mode: Int) {
        val mmkv = MMKV.mmkvWithID("app")
        mmkv.encode("mode", mode)
    }

    /***
     * 获取是否请求置顶
     */
    suspend fun getRequestTop(context: Context): Boolean {
        val requestTop: Flow<Boolean> = DataStoreUtils.getRequestTop(context)
        var isRequestTop: Boolean = false
        requestTop.collect { value ->
            isRequestTop = value
        }
        return isRequestTop
    }

    fun getColorStateList(context: Context): ColorStateList {
        val colors =
            intArrayOf(getColor(context), ContextCompat.getColor(context, R.color.colorAccent))
        val states = arrayOfNulls<IntArray>(2)
        states[0] = intArrayOf(android.R.attr.state_checked, android.R.attr.state_checked)
        states[1] = intArrayOf()
        return ColorStateList(states, colors)
    }

    fun getColorStateList(color: Int): ColorStateList {
        val colors =
            intArrayOf(color, ContextCompat.getColor(Utils.getApp(), R.color.colorAccent))
        val states = arrayOfNulls<IntArray>(2)
        states[0] = intArrayOf(android.R.attr.state_checked, android.R.attr.state_checked)
        states[1] = intArrayOf()
        return ColorStateList(states, colors)
    }

    fun getOneColorStateList(context: Context): ColorStateList {
        val colors = intArrayOf(getColor(context))
        val states = arrayOfNulls<IntArray>(1)
        states[0] = intArrayOf()
        return ColorStateList(states, colors)
    }

    fun getOneColorStateList(color: Int): ColorStateList {
        val colors = intArrayOf(color)
        val states = arrayOfNulls<IntArray>(1)
        states[0] = intArrayOf()
        return ColorStateList(states, colors)
    }

    /***
     * 设置shap文件颜色
     * @param view
     * @param color
     */
    fun setShapColor(view: View, color: Int) {
        val drawable = view.background as GradientDrawable
        drawable.setColor(color)
    }

    /***
     * @param view 需要 Shap的View
     * @param color 渐变颜色数组
     * @param orientation 渐变方向
     */
    fun setShapColor(view: View, color: IntArray, orientation: GradientDrawable.Orientation) {
        val drawable = view.background as GradientDrawable
        drawable.orientation = orientation
        drawable.colors = color
    }

    /***
     * 设置 selector 文件
     *  Int::class.javaPrimitiveType
     *  返回一个Int类型的java 实例
     * @param view
     * @param yesColor
     * @param noColor
     */
    fun setSelectorColor(view: View, yesColor: Int, noColor: Int) {
        val mySelectorGrad = view.background as StateListDrawable
        try {

            val slDrawable = StateListDrawable::class.java
            val getStateCountMethod =
                slDrawable.getDeclaredMethod("getStateCount", *arrayOfNulls(0))
            val getStateSetMethod =
                slDrawable.getDeclaredMethod("getStateSet", Int::class.javaPrimitiveType)
            val getStateDrawableMethod =
                slDrawable.getDeclaredMethod("getStateDrawable", Int::class.javaPrimitiveType)
            val count = getStateCountMethod.invoke(mySelectorGrad) as Int//对应item标签
            for (i in 0 until count) {
                val stateSet = getStateSetMethod.invoke(mySelectorGrad, i) as IntArray//对应Item 标签
                if (stateSet.isEmpty()) {
                    //这就是你要获得的Enabled为false时候的drawable
                    val drawable =
                        getStateDrawableMethod.invoke(mySelectorGrad, i) as GradientDrawable
                    drawable.setColor(yesColor)
                } else {
                    for (i in stateSet.indices) {
                        val drawable =
                            getStateDrawableMethod.invoke(mySelectorGrad, i) as GradientDrawable
                        drawable.setColor(noColor)
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     *设置透明颜色
     * @param color 颜色
     * @param transparent 透明度  0-1
     */
    fun translucentColor(color: Int, transparent: Float): Int {
        val alpha = (Color.alpha(color) * transparent).roundToInt()
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }


}