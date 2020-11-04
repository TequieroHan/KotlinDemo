package com.my_go.mylibrary.utils

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.my_go.mylibrary.utils.StringUtils.isEmail
import com.my_go.mylibrary.utils.StringUtils.isNotEmpty
import com.my_go.mylibrary.utils.StringUtils.isNumberOrAlpha
import com.my_go.mylibrary.utils.StringUtils.isPhone
import java.lang.Exception

/**
 * Create by Package com.my_go.mylibrary.utils
 * Created by 毛勇 on 2020/10/30
 * Current System Time 9:50
 * Describe:通用密码、手机号、验证码输入框输入字符判断及错误提示 类
 * 1、隐藏输入法
 * 2、显示输入框
 * 3、显示/隐藏输入框
 * 4、显示输入框
 * 5、显示/隐藏输入框
 * 6、判断edittext输入文字是否合法
 * 7、字符不合法提示
 */
class EditTextUtils {

    companion object {
        //TODO 显示/隐藏输入框-------------------------------------------------
        var imm: InputMethodManager? = null

        /**
         * 隐藏输入法
         * @param toGoWindowToken 根据toGoWindowToken 的位置显示/隐藏
         */
        fun hindKeyboard(context: Context, toGoWindowToken: View?) {
            showKeyboard(context, null, toGoWindowToken, false)
        }

        /**
         *显示 软件盘
         * @param toGoWindowToken 为NUL 时 toGoWindowToken = et ，根据toGoWindowToken 的位置显示/隐藏
         * @param isShow
         */
        fun showKeyboard(
            context: Context?,
            et: EditText?,
            toGoWindowToken: View?,
            isShow: Boolean,
        ) {
            var goWindowToken = toGoWindowToken
            when (context) {
                null -> {
                    return;
                }
            }
            imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager


            when (goWindowToken) {
                null -> {
                    goWindowToken = et
                }
            }
            when (goWindowToken) {
                null -> {
                    return
                }
            }
            when (isShow) {
                false -> {
                    imm!!.hideSoftInputFromWindow(goWindowToken?.windowToken, 0)
                    when {
                        null != et -> {
                            et.clearFocus()
                        }
                    }
                }
                else -> {
                    when {
                        null != et -> {
                            et.isFocusable = true
                            et.isFocusableInTouchMode = true
                            et.requestFocus()
                            imm!!.toggleSoftInputFromWindow(
                                goWindowToken?.windowToken,
                                InputMethodManager.SHOW_FORCED,
                                InputMethodManager.HIDE_IMPLICIT_ONLY
                            )
                        }
                    }
                }
            }

        }
        //TODO 显示/隐藏输入框-------------------------------------------------

        private var oringinalHintColor: ColorStateList? = null
        const val TYPE_NOT_ALLOWED_EMPTY: Int = 0 //不允许为NULL
        const val TYPE_VERIFY: Int = 1//验证码
        const val TYPE_PASSWORD: Int = 2//密码
        const val TYPE_PHONE: Int = 3//电话
        const val TYPE_MAIL: Int = 4//邮箱

        /**
         * 判断et 输入文字是否合法
         */
        fun isInputedCorrect(activity: Activity?, et: EditText?): Boolean? {
            return isInputedCorrect(activity, et, TYPE_NOT_ALLOWED_EMPTY, null)
        }

        /***
         * 判断et 输入文字是否合法
         * @param activity
         * @param et
         * @param type
         * @param errorRemind
         */
        fun isInputedCorrect(
            context: Activity?,
            et: EditText?,
            type: Int?,
            errorRemind: String?,
        ): Boolean? {
            when {
                context == null || et == null -> {
                    return false
                }
            }
            oringinalHintColor = et?.hintTextColors
            var inputed: String? = StringUtils.getTrimedString(et)
            when (type) {
                TYPE_VERIFY -> if (type === TYPE_VERIFY && inputed!!.length < 4) {
                    return showInputedError(context,
                        et,
                        if (isNotEmpty(errorRemind, true)!!) errorRemind else "验证码不能小于4位")
                }
                TYPE_PASSWORD -> {
                    if (inputed!!.length < 6) {
                        return showInputedError(context,
                            et,
                            if (isNotEmpty(errorRemind, true)!!) errorRemind else "密码不能小于6位")
                    }
                    if (isNumberOrAlpha(inputed) == false) {
                        return showInputedError(context,
                            et,
                            if (isNotEmpty(errorRemind, true)!!) errorRemind else "密码只能含有字母或数字")
                    }
                }
                TYPE_PHONE -> {
                    if (inputed!!.length != 11) {
                        return showInputedError(context,
                            et,
                            if (isNotEmpty(errorRemind, true)!!) errorRemind else "请输入11位手机号")
                    }
                    if (isPhone(inputed) == false) {
                        Toast.makeText(context, "您输入的手机号格式不对哦~", Toast.LENGTH_SHORT).show()
                        return false
                    }
                }
                TYPE_MAIL -> if (isEmail(inputed) == false) {
                    return showInputedError(context, "您输入的邮箱格式不对哦~")
                }
                else -> if (isNotEmpty(inputed,
                        true) == false || inputed == StringUtils.getTrimedString(
                        et!!.hint)
                ) {
                    return showInputedError(context,
                        et,
                        if (isNotEmpty(errorRemind,
                                true)!!
                        ) errorRemind else StringUtils.getTrimedString(et))
                }
            }
            et!!.setHintTextColor(oringinalHintColor)
            return true
        }

        fun showInputedError(context: Activity?, resid: Int?): Boolean? {
            return showInputedError(context, null, resid)
        }

        fun showInputedError(context: Activity?, string: String?): Boolean? {
            return showInputedError(context, null, string)
        }

        fun showInputedError(context: Activity?, et: EditText?, resId: Int?): Boolean? {
            try {
                return showInputedError(context, et, context?.resources?.getString(resId!!))
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return false
        }

        /***
         * *字符不合法提示
         */
        fun showInputedError(
            activity: Activity?,
            et: EditText?,
            errorRemind: String?,
        ): Boolean? {
            return when {
                activity == null || StringUtils.isNotEmpty(errorRemind, false) == false -> {
                    false
                }
                else -> {
                    when (et) {
                        null -> {
                            Toast.makeText(activity,
                                StringUtils.getTrimedString(errorRemind),
                                Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            et.setText("")
                            et.setHint(errorRemind)
                            et.setHintTextColor(activity.resources.getColor(android.R.color.holo_red_dark))
                        }
                    }
                    false
                }
            }
        }

    }
}