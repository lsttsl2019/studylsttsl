package com.lsttsl.study_01.recycelrViewItem.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lsttsl.study_01.activtiy.MainActivity
import com.lsttsl.study_01.databinding.TodoReyclerviewItemBinding
import com.lsttsl.study_01.recycelrViewItem.item.TodoItem

class TodoAdapter(private val todoListInfo: ArrayList<TodoItem>) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {
    private lateinit var mContext: Context


    inner class ViewHolder(var binding: TodoReyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun updateImage(resource: Int) {
            Glide.with(mContext)
                .asBitmap()
                .load(resource)
                .into(BitmapCustomImg(binding.todoImg))
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

        val binding =
            TodoReyclerviewItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = todoListInfo[position]
        holder.binding.todoTitle.text = item.title
        holder.binding.todoDay.text = item.day
        holder.updateImage(item.img)

        holder.binding.todoLayout.setOnClickListener {
            if (mContext is MainActivity) {
                (mContext as MainActivity).todoItemOnClickAnimation(true)
            }
        }


    }

    override fun getItemCount(): Int {
        return todoListInfo.size
    }

}