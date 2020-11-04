package com.my_go.kotlin.ui.home.entity

import com.my_go.mylibrary.entity.BaseResponse

/**
 * Create by Package com.my_go.kotlin.ui.home.entity
 * Created by 毛勇 on 2020/11/4
 * Current System Time 13:48
 * Describe:
 */
class BannerEntity : BaseResponse() {

    var imageUrl: String? = ""
        set(value) {
            field = value
        }
        get() = field
    var disTitle: String? = ""
        set(value) {
            field = value
        }
        get() = field
}