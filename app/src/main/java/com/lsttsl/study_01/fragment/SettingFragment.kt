package com.lsttsl.study_01.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lsttsl.study_01.databinding.SettingFragmentLayoutBinding

class SettingFragment :Fragment() {

    private  var binding : SettingFragmentLayoutBinding? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingFragmentLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
    companion object {

        fun instance() = SettingFragment()

    }

}