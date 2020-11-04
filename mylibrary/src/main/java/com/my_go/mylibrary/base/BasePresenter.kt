package com.my_go.mylibrary.base


/**
 * Create by Package com.my_go.mylibrary.base
 * Created by 毛勇 on 2020/10/28
 * Current System Time 17:24
 * Describe:
 */
abstract class BasePresenter<T> {
    var view: T? = null
        get() {
            return field
        }

    /**
     * 绑定视图层
     */
    fun onAttach(v: T?) {
        this.view = v
    }

    /**
     * 解绑View层
     */
    fun onDetch() {
        when {
            null != view -> {
                this.view = null;
            }
        }
    }


}