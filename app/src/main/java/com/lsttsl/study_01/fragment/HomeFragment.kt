package com.lsttsl.study_01.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsttsl.study_01.databinding.HomeFragmentLayoutBinding
import com.lsttsl.study_01.recycelrViewItem.adapter.HomeAdapter
import com.lsttsl.study_01.recycelrViewItem.item.HomeItem

class HomeFragment : Fragment() {

    private var binding: HomeFragmentLayoutBinding? = null

    private var homeListInfo: ArrayList<HomeItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentLayoutBinding.inflate(inflater, container, false)
        return binding?.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeListInfo = arguments!!.getParcelableArrayList<HomeItem>("homeData")
        Log.d(TAG, "onViewCreated: ${homeListInfo.toString()}")
        createReyclerView(homeListInfo!!)
    }


    private fun createReyclerView(homeListInfo: ArrayList<HomeItem>) {
        val homeAdapter = HomeAdapter(homeListInfo)
        val linearLayoutManager=LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.homeRecyclerView?.layoutManager = linearLayoutManager
        binding?.homeRecyclerView?.adapter = homeAdapter

    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {

        fun instance() = HomeFragment()
        private val TAG = HomeFragment::class.java.simpleName
    }

}