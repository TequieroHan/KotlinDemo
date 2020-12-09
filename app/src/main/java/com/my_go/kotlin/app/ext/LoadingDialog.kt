package com.my_go.kotlin.app.ext

import android.app.Activity
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.my_go.kotlin.R
import com.my_go.kotlin.utils.SettingUtil

/**
 * Create by Package com.my_go.kotlin.app.ext
 * Created by 毛勇 on 2020/12/1
 * Current System Time 13:42
 * Describe:
 */

private var loadingDialog: MaterialDialog? = null

/**
 * 打开等待弹窗
 */
fun AppCompatActivity.showLoadingExt(message: String = "请求网络中..") {
    if (!this.isFinishing) {
        if (loadingDialog == null) {
            loadingDialog = MaterialDialog(this)
                .cancelable(true)
                .cancelOnTouchOutside(true)
                .cornerRadius(resources.getDimension(R.dimen.dp_12))
                .customView(R.layout.layout_custom_progress_dialog_view)
                .lifecycleOwner(this)
            loadingDialog?.getCustomView()?.run {
                this.findViewById<TextView>(R.id.loading_tips).text = message
                this.findViewById<ProgressBar>(R.id.progressBar).indeterminateTintList =
                    SettingUtil.getOneColorStateList(this@showLoadingExt)

            }
        }
        loadingDialog?.show()
    }
}


/**
 * 打开等待弹窗
 */
fun Fragment.showLoadingExt(message: String = "请求网络中..") {
    activity?.let {
        if (!it.isFinishing) {
            if (loadingDialog == null) {
                loadingDialog = MaterialDialog(it)
                    .cancelable(true)
                    .cancelOnTouchOutside(true)
                    .cornerRadius(it.resources.getDimension(R.dimen.dp_12))
                    .customView(R.layout.layout_custom_progress_dialog_view)
                    .lifecycleOwner(this)
                loadingDialog?.getCustomView()?.run {
                    this.findViewById<TextView>(R.id.loading_tips).text = message
                    this.findViewById<ProgressBar>(R.id.progressBar).indeterminateTintList =
                        SettingUtil.getOneColorStateList(it)
                }
            }
            loadingDialog?.show()
        }
    }
}

/**
 *关闭等待框
 */
fun Activity.dismissLoadingExt() {
    loadingDialog?.dismiss()
    loadingDialog = null
}

/**
 * 关闭等待框
 */
fun Fragment.dismissLoadingExt() {
    loadingDialog?.dismiss()
    loadingDialog = null
}