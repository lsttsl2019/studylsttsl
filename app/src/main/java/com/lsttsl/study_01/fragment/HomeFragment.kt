package com.lsttsl.study_01.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsttsl.study_01.databinding.HomeFragmentLayoutBinding
import com.lsttsl.study_01.databinding.HomeReyclerviewItemBinding
import com.lsttsl.study_01.recycelrViewItem.adapter.HomeAdapter
import com.lsttsl.study_01.recycelrViewItem.item.HomeItem
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var binding: HomeFragmentLayoutBinding? = null

    private var homeListInfo: ArrayList<HomeItem>? = null

    private lateinit var homeAdapter: HomeAdapter

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
        createReyclerView(homeListInfo!!)
    }


    private fun createReyclerView(homeListInfo: ArrayList<HomeItem>) {
        homeAdapter = HomeAdapter(homeListInfo)
        homeAdapter.setHasStableIds(true)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.homeRecyclerView?.layoutManager = linearLayoutManager
        binding?.homeRecyclerView?.adapter = homeAdapter


    }

    fun refreshHomeFragment(isPopupCheck: Boolean) {

        // TODO: 1/3/20 지금은 단순히 데이터를 오름차순 내리차순으로 변경할뿐이다...
        // 서버에 연결된 데이터를 받으면 실제적으로 데이터 변경이 필요함..
        // 단순한 변경일뿐.. 날짜 데이터로 변경해야하지만 지금은 오름차순 내리차순으로 변경.....
        val oldListInfo = ArrayList<HomeItem>()
        for (i in homeListInfo!!.size - 1 downTo 0) {
            oldListInfo.add(homeListInfo!![i])
        }
        homeListInfo!!.clear()
        for (i in 0 until oldListInfo.size) {
            homeListInfo?.add(oldListInfo[i])
        }
        homeAdapter.notifyDataSetChanged()


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