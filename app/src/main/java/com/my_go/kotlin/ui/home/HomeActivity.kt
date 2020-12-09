package com.my_go.kotlin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ToastUtils
import com.my_go.kotlin.R
import com.my_go.kotlin.app.base.BaseActivity
import com.my_go.kotlin.data.model.bean.NavBar
import com.my_go.kotlin.databinding.ActivityHomeBinding
import com.my_go.kotlin.utils.SettingUtil
import com.my_go.kotlin.viewmodel.state.HomeViewModel
import com.my_go.mvvmlibrary.network.manager.NetState
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.include_home_page.*


/**
 * Create by Package com.my_go.kotlin.ui.home.entity
 * Created by 毛勇 on 2020/11/19
 * Current System Time 23:13
 * Describe:主页面
 * ActivityMainBinding -> activity_main.xml
 */
class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {

    //碎片集合
    var fragments: ArrayList<Fragment> = arrayListOf()

    //集合标题
    var titles: ArrayList<NavBar> = arrayListOf()

    override fun layoutId(): Int {
        return R.layout.activity_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        btn.setOnClickListener(View.OnClickListener {
            var color = SettingUtil.getColor(this@HomeActivity)
            Log.e("TAG", "Color =" + color)
        })
        initNavTitles()
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

            }
        })
    }

    private fun initNavTitles() {
        titles.add(
            NavBar(
                getString(R.string.nav_home),
                R.drawable.ic_nav_home_light,
                R.drawable.ic_nav_home_nomal
            )
        )
        titles.add(
            NavBar(
                "个人中心",
                R.drawable.ic_personal_center_light,
                R.drawable.ic_personal_center_nomal
            )
        )
//        view_pager.init(this,fragments)
//        magic_indicator.bi
    }

    override fun createObserver() {

    }

    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (!netState.isSuccess) {
            ToastUtils.showLong(getString(R.string.hint_what_disconnection))
        }
    }
}