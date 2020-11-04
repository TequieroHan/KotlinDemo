package com.my_go.kotlin.adapter

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my_go.kotlin.R
import com.my_go.kotlin.ui.home.entity.BannerEntity
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.adapter.BannerImageAdapter
import kotlinx.android.synthetic.main.item_banner_image_text.view.*
import kotlin.coroutines.Continuation

/**
 * Create by Package com.my_go.kotlin.adapter
 * Created by 毛勇 on 2020/11/4
 * Current System Time 13:40
 * Describe:Banner 图片 + Title
 */
class BannerImageTitleAdapter(context: Context?, banners: ArrayList<BannerEntity>) :
    BannerAdapter<BannerEntity, BannerImageTitleAdapter.ImageTitleHolder>(banners) {
    var mContext: Context? = context
    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ImageTitleHolder {

        return ImageTitleHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_banner_image_text, parent, false))
    }

    override fun onBindView(
        holder: ImageTitleHolder?,
        data: BannerEntity?,
        position: Int,
        size: Int,
    ) {
        Glide.with(mContext!!).load(data?.imageUrl).into(holder?.imageView!!)
        holder.tv.setText(data?.disTitle)
    }


    class ImageTitleHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView
        var tv: TextView

        init {
            imageView = view.findViewById(R.id.iv) as ImageView
            tv = view.findViewById(R.id.tv) as TextView
        }

    }

}