package com.lsttsl.study_01.recycelrViewItem.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lsttsl.study_01.R
import com.lsttsl.study_01.databinding.HomeReyclerviewItemBinding
import com.lsttsl.study_01.recycelrViewItem.item.HomeItem

class HomeAdapter(private val homeItemList: ArrayList<HomeItem>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    private lateinit var mContext: Context

    inner class ViewHolder(var binding: HomeReyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun updateImage(resource: Int) {
            Glide.with(mContext).asBitmap()
                .load(resource)
                .into(ImageCreate(binding.homeImg))
        }

    }




    inner class ImageCreate(private val imageView: ImageView) : CustomTarget<Bitmap>() {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            imageView.setImageBitmap(resource)
        }

        override fun onLoadCleared(placeholder: Drawable?) {

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding =
            HomeReyclerviewItemBinding.inflate(LayoutInflater.from(mContext), parent, false)


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = homeItemList[position]
        holder.binding.homeTitle.text = item.title
        holder.binding.homeDay.text = item.day
        // TODO: 2020-12-30  이미지 작업들어가야함...
        holder.updateImage(item.img)
    }

    override fun getItemCount(): Int {
        Log.d("Home", "getItemCount: ${homeItemList.size}")
        return homeItemList.size
    }

}