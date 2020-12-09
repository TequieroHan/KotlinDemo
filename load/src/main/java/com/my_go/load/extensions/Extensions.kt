package com.my_go.load.extensions

import android.animation.Animator
import android.animation.ObjectAnimator
import android.view.View
import android.widget.RelativeLayout

/**
 * Create by Package com.my_go.load.extensions
 * Created by 毛勇 on 2020/11/19
 * Current System Time 22:59
 * Describe:
 */

fun View.setLeftMargin(left: Int) {
    val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
    params.setMargins(left, 0, 0, 0)
    layoutParams = params
}

fun View.getLeftMargin() : Int {
    return (layoutParams as RelativeLayout.LayoutParams).leftMargin
}

inline fun ObjectAnimator.onEnd(crossinline func: () -> Unit) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) { func() }
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationStart(animation: Animator?) {}
    })
}

