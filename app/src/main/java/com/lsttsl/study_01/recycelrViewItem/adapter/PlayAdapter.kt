package com.lsttsl.study_01.recycelrViewItem.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lsttsl.study_01.databinding.PlayerFragmentItemBinding
import com.lsttsl.study_01.recycelrViewItem.item.PlayerItem

class PlayAdapter(private val playList: ArrayList<PlayerItem>) :
    RecyclerView.Adapter<PlayAdapter.ViewHolder>() {
    private lateinit var mContext: Context
    private lateinit var binding: PlayerFragmentItemBinding
    private var mListener : OnItemClickListener? = null

    fun setOnItemClickListener( listener: OnItemClickListener){
        this.mListener = listener
    }

    inner class ViewHolder(var binding: PlayerFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun updateImage(resource: Int) {
            Glide.with(mContext)
                .asBitmap()
                .load(resource)
                .into(BitmapCustomImg(binding.playImg))
        }
        init {
//            binding.playPlayingImg.setOnClickListener()
//            binding.playPauseImg.setOnClickListener()
        }

    }

    inner class BitmapCustomImg(private val imageView: ImageView) : CustomTarget<Bitmap>() {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            imageView.setImageBitmap(resource)
        }

        override fun onLoadCleared(placeholder: Drawable?) {

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        binding = PlayerFragmentItemBinding.inflate(LayoutInflater.from(mContext), parent, false)

        return ViewHolder(binding)

    }


    private var position = 0
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val playInfo = playList[position]
        holder.binding.playTitle.text = playInfo.title
        holder.binding.playTitleSecond.text = playInfo.titleSecond
        holder.updateImage(playInfo.imgUrl)

        this.position = position
    }

    fun firstInfo(): PlayerItem {
        return playList[position]
    }

    fun getPagePlayInfo(position: Int): PlayerItem {
        return playList[position]
    }

    override fun getItemCount(): Int {
        return playList.size
    }


    var isPausePlaying: Boolean = false
        set(value) {
            if (value) {
                binding.playPauseImg.visibility = View.VISIBLE
                binding.playPlayingImg.visibility = View.INVISIBLE

            } else {

                binding.playPauseImg.visibility = View.INVISIBLE
                binding.playPlayingImg.visibility = View.VISIBLE

            }

            field = value
        }



    inner class OnItemClickListener {
        fun onItemClick(view: View, position: Int){}
    }



}