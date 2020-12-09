package com.my_go.kotlin.app.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * Create by Package com.my_go.kotlin.app.ext
 * Created by 毛勇 on 2020/12/1
 * Current System Time 15:13
 * Describe:项目中自定义类的拓展函数
 */


/**
 * 隐藏软件盘
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { at ->
        val view = at.currentFocus
        view?.let {
            val inputMethodManager =
                at.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}