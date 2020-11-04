package com.my_go.mylibrary.entity

/**
 * Create by Package com.my_go.mylibrary.entity
 * Created by 毛勇 on 2020/11/4
 * Current System Time 13:55
 * Describe:
 */
open class BaseResponse {
    constructor()

    var code: String? = null
        set(code) {
            field = code
        }
        get() = field
    var msg: String? = null
        set(value) {
            field = value
        }
        get() = field
}