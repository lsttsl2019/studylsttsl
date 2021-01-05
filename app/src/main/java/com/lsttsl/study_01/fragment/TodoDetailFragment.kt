package com.lsttsl.study_01.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lsttsl.study_01.activtiy.MainActivity
import com.lsttsl.study_01.activtiy.PlayerActivity
import com.lsttsl.study_01.databinding.TodoFragmentDetailBinding

class TodoDetailFragment : Fragment() {


    private var binding: TodoFragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TodoFragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onDetailBtnEvent()

    }


    private var isTopLayout = false
        set(value) = if (value) binding?.detailTopLayout?.visibility =
            View.GONE else binding?.detailTopLayout?.visibility = View.VISIBLE

    private var isPlayPauseCheck = false
        set(value) {

            if (value) {
                (activity as MainActivity).todoDetailFragmentOnClickAnimation(true)
                binding?.detailPlayingImg?.visibility = View.GONE
                binding?.detailPauseImg?.visibility = View.VISIBLE

            } else {
                (activity as MainActivity).todoDetailFragmentOnClickAnimation(false)
                binding?.detailPlayingImg?.visibility = View.VISIBLE
                binding?.detailPauseImg?.visibility = View.GONE
            }
            field = value
        }

    fun isPause() {
        isPlayPauseCheck = false
    }


    private fun onDetailBtnEvent() {
        binding?.detailPhoneImg?.setOnClickListener(onClickListener)
        binding?.todoTopDetailClose?.setOnClickListener(onClickListener)
        binding?.detailPlayingImg?.setOnClickListener(onClickListener)
        binding?.detailPauseImg?.setOnClickListener(onClickListener)
        binding?.detailHeadsetImg?.setOnClickListener(onClickListener)
    }


    private val onClickListener = View.OnClickListener { v ->
        when (v) {
            binding?.detailPhoneImg -> {
                val phoneNumber = binding?.detailPhoneNumber?.text.toString()
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(intent)
            }
            binding?.todoTopDetailClose -> {
                isTopLayout = true
            }
            binding?.detailPlayingImg -> {
                isPlayPauseCheck = true
            }

            binding?.detailPauseImg -> {
                isPlayPauseCheck = false
            }
            binding?.detailHeadsetImg -> {
                goToPlayer()
            }

        }
    }


    fun goToPlayer(){
        if (isPlayPauseCheck) {
            isPlayPauseCheck = false
        }
        val intent = Intent(context, PlayerActivity::class.java)
        startActivity(intent)
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


    companion object {
        fun instance() = TodoDetailFragment()
        private val TAG = TodoDetailFragment::class.simpleName
    }


}