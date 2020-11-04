package com.my_go.kotlin.ui.home

import android.os.Bundle
import com.my_go.kotlin.R.layout.activity_main
import com.my_go.kotlin.adapter.BannerImageTitleAdapter
import com.my_go.kotlin.ui.home.entity.BannerEntity
import com.my_go.mylibrary.base.BaseActivity
import com.my_go.mylibrary.base.BasePresenter
import com.my_go.mylibrary.base.BaseView
import com.youth.banner.transformer.AlphaPageTransformer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Create by Package com.my_go.kotlin.ui.home
 * Created by 毛勇 on 2020/10/26
 * Current System Time 20:34
 * Describe:
 */
class HomeActivity<T : BaseView, P : BasePresenter<T>> : BaseActivity<T, P>() {

    var bannerImageTitle: ArrayList<BannerEntity> = ArrayList<BannerEntity>();

    override fun bindPresenter(): P? {
        return null
    }

    override fun bindLayout(): Int {
        return activity_main
    }

    override fun initParam(savedInstanceState: Bundle?) {

    }

    override fun initView() {
        initBanner()
    }

    private fun initBanner() {
        var bit = BannerEntity()
        bit.disTitle = "011ad05e27a173a801216518a5c505"
        bit.imageUrl =
            "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1604477280&di=4de061c1773cfdd7930e195983b97171&src=http://media-cdn.tripadvisor.com/media/photo-s/01/3e/05/40/the-sandbar-that-links.jpg"
        bannerImageTitle.add(bit)
        bannerImageTitle.add(bit)
        bannerImageTitle.add(bit)
        bannerImageTitle.add(bit)
        bannerImageTitle.add(bit)
        var adapter = BannerImageTitleAdapter(this, bannerImageTitle)

        banner?.let {
            it.adapter = adapter
            it.setBannerGalleryEffect(18, 10)
            it.addPageTransformer( AlphaPageTransformer())
        }
    }


    override fun initData() {
    }


}