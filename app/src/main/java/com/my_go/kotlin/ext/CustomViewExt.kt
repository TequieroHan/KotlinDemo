package com.my_go.kotlin.ext

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.afollestad.materialdialogs.utils.MDUtil.inflate
import com.my_go.kotlin.R
import com.my_go.kotlin.app.weight.viewpager.ScaleTransitionPagerTitleView
import com.my_go.kotlin.data.model.bean.NavBar
import com.my_go.mvvmlibrary.base.application
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView.OnPagerTitleChangeListener

/**
 * Create by Package com.my_go.kotlin.ext
 * Created by 毛勇 on 2020/12/2
 * Current System Time 15:26
 * Describe:
 */
class CustomViewExt {

    fun MagicIndicator.bindViewPager2(
        viewPager2: ViewPager2,
        mDataList: ArrayList<NavBar> = arrayListOf(),
        mStringList: ArrayList<String> = arrayListOf(),
        action: (index: Int) -> Unit = {}
    ) {
        val commonNavigator = CommonNavigator(application)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return if (mDataList.size != 0) {
                    mDataList.size
                } else {
                    mStringList.size
                }
            }

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val commonPagerTitleView = ScaleTransitionPagerTitleView(context, mDataList)
                    .inflate(
                        ctxt = context!!,
                        res = R.layout.bottom_nav_title_imamge
                    ) as ScaleTransitionPagerTitleView

                return commonPagerTitleView.apply {

                    setOnClickListener {
                        viewPager2.currentItem = index
                        action.invoke(index)
                    }
                }
            }

            override fun getIndicator(context: Context?): IPagerIndicator? {
                return null
            }
        }
        this.navigator = commonNavigator
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                this@bindViewPager2.onPageSelected(position)
                action.invoke(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                this@bindViewPager2.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                this@bindViewPager2.onPageScrollStateChanged(state)
            }
        })
    }

    fun ViewPager2.init(
        fragment: Fragment,
        fragments: ArrayList<Fragment>,
        isUserInputEnabled: Boolean = true
    ): ViewPager2 {
        this.isUserInputEnabled = isUserInputEnabled
        adapter = object : FragmentStateAdapter(fragment) {
            override fun getItemCount(): Int {
                return fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }
        return this
    }
}