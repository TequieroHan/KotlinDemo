package com.my_go.kotlin.base

import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import com.my_go.kotlin.utils.ToolUtils
import com.my_go.mylibrary.base.BaseActivity

/**
 * Create by Package com.my_go.kotlin.base
 * Created by 毛勇 on 2020/10/26
 * Current System Time 21:58
 * Describe:
 */
abstract class MyBaseActivity : BaseActivity() {


    /**
     * 点击空白处，隐藏软件盘
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val mInputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager
        if (event?.action == MotionEvent.ACTION_DOWN) {
            if (currentFocus != null && currentFocus?.windowToken != null) {
                mInputMethodManager?.hideSoftInputFromWindow(
                    currentFocus?.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }

        return super.onTouchEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus as? View;
            if (ToolUtils.isShouldHideKeyboard(v, ev)) {
                ToolUtils.hideSoftInput(this);
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}