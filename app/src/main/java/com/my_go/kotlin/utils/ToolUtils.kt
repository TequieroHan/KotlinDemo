package com.my_go.kotlin.utils

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Package com.my_go.kotlin.utils
 * Created by 毛勇 on 2020/10/27
 * Current System Time 9:19
 * Describe:工具类：
 * private constructor() 方法等价于 private ToolUtils()
 */
class ToolUtils private constructor() {
    /**
     * Kotlin 静态内部单例
     */
    companion object {
        private var mContext: AppCompatActivity? = null;
        private var instance: ToolUtils? = null
            get() {
                if (field == null) {
                    field = ToolUtils();
                }
                return field;
            }

        //通过子类返回外部类的实例 !!表示不能为NULL 如果为空则会抛出异常
        @Synchronized
        fun getInst(mContext: AppCompatActivity?): ToolUtils {
            this.mContext = mContext;
            return instance!!
        }

        /**
         * 根据View所在坐标和用户点击坐标来相比，
         * 判断是否隐藏软件盘，
         * 但用户点击View时不能隐藏软件盘
         * @param v 视图 View? 表示 视图可以为null
         * @param event 事件
         * @return boolean
         */
        fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
            //v is EditText 等同于 v instanceof EditText
            if (v != null && v is EditText) {
                //var xy: IntArray = intArrayOf(0, 0) 等同于 int[] xy={0,0}
                var xy: IntArray = intArrayOf(0, 0)
                v.getLocationInWindow(xy)
                var left: Int = xy[0]
                var top: Int = xy[1]
                var bottom: Int = top + v.height
                var right: Int = left + v.width
                return event.x <= left || event.x >= right || event.y <= top || event.y >= bottom
            }
            // 如果焦点不是EditText则忽略，
            // 这个发生在视图刚绘制完，
            // 第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
            return false;
        }

        /***
         * 隐藏软件盘
         * ！！两个感叹号为非空断言操作符，强制作为非空值看待。如果不正确，则引发异常。
         * ?.安全调用操作符 调用方法并在值为空时返回NULL
         */
        fun hideSoftInput(mContext: AppCompatActivity?) {
            val imm =
                mContext?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager
            if (mContext?.currentFocus != null) {
                imm?.hideSoftInputFromWindow(mContext?.currentFocus!!.windowToken, 0)
            }
        }

    }
}