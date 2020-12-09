package com.my_go.kotlin.app.weight.viewpager

import android.content.Context
import com.my_go.kotlin.R
import com.my_go.kotlin.data.model.bean.NavBar
import kotlinx.android.synthetic.main.bottom_nav_title_imamge.view.*
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView

/**
 * Create by Package com.my_go.kotlin.app.weight.viewpager
 * Created by 毛勇 on 2020/12/2
 * Current System Time 16:46
 * Describe:
 */
class ScaleTransitionPagerTitleView(context: Context?, navBars: List<NavBar>) :
    CommonPagerTitleView(context) {
    var mNavBars: List<NavBar> = navBars

    override fun onSelected(index: Int, totalCount: Int) {
        title_tv.setTextColor(resources.getColor(R.color.color_17abe3))
        if (!mNavBars.get(index).title.equals("")) {
            title_img.setBackgroundResource(mNavBars.get(index).light)
        }
    }

    override fun onDeselected(index: Int, totalCount: Int) {
        title_tv.setTextColor(resources.getColor(R.color.color_4b4b4b))
        if (!mNavBars.get(index).title.equals("")) {
            title_img.setBackgroundResource(mNavBars.get(index).nomal)
        }
    }
}