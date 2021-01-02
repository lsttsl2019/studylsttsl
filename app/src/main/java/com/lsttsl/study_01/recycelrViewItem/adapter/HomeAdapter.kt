package com.lsttsl.study_01.recycelrViewItem.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.lsttsl.study_01.R
import com.lsttsl.study_01.activtiy.MainActivity
import com.lsttsl.study_01.databinding.HomeReyclerviewItemBinding
import com.lsttsl.study_01.fragment.TodoFragment
import com.lsttsl.study_01.recycelrViewItem.item.HomeItem
import com.lsttsl.study_01.recycelrViewItem.item.TodoItem

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
        holder.updateImage(item.img)

        holder.binding.homeItemLayout.setOnClickListener {
            Toast.makeText(mContext, "${position}에 클릭", Toast.LENGTH_SHORT).show()

            //원래 서버 통신해서.. 데이터를 뽑아서 넘겨야하지만 지금은 고정된 데이터를 추가해주자
            // TODO: 2020-12-31 데이터 통신으로 넘겨주자...
            if (mContext is MainActivity) {
                val fragment = TodoFragment.instance()
                val todoItem = TodoItem(item.title, item.day, item.img)

                val todoListInfo: ArrayList<TodoItem> = (mContext as MainActivity).getTodoItem()
                todoListInfo[0] = todoItem
                val bundle = Bundle()
                bundle.putParcelableArrayList("todoData", todoListInfo)
                fragment.arguments = bundle
                (mContext as MainActivity).changeFragment(fragment )
            }
        }
    }


    override fun getItemCount(): Int {
        return homeItemList.size
    }

    companion object {
        private val TAG = HomeAdapter::class.simpleName
    }

}