package com.lsttsl.study_01.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.lsttsl.study_01.databinding.TodoFragmentLayoutBinding
import com.lsttsl.study_01.recycelrViewItem.adapter.TodoAdapter
import com.lsttsl.study_01.recycelrViewItem.item.TodoItem

class TodoFragment : Fragment() {

    private var binding: TodoFragmentLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TodoFragmentLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val todoListInfo: ArrayList<TodoItem>? = arguments!!.getParcelableArrayList("todoData")
        if (todoListInfo != null) {
            createReView(todoListInfo)
        }


    }

    private fun createReView(todoListInfo: ArrayList<TodoItem>) {
        val todoAdapter = TodoAdapter(todoListInfo)

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL

        binding?.todoRecyclerview?.layoutManager = linearLayoutManager
        binding?.todoRecyclerview?.adapter = todoAdapter


    }

    fun returnTodoBinding() : TodoFragmentLayoutBinding? {
        return binding
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {

        fun instance() = TodoFragment()
        private val TAG = TodoFragment::class.simpleName

    }

}