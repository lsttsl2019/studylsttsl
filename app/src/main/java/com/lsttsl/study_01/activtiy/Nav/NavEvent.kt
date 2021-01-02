package com.lsttsl.study_01.activtiy.Nav

import android.view.View
import androidx.core.os.bundleOf
import com.lsttsl.study_01.databinding.ActivityMainBinding

class NavEvent(private val binding: ActivityMainBinding) {

    var isNavHomeEvent: Boolean = false
        set(value) {

            if (value) {
                binding.homeLayoutClickOk.visibility = View.VISIBLE
                binding.homeLayout.visibility = View.GONE
                isNavTodoEvent = false
                isNavMapEvent = false
                isNavMessageEvent = false
                isNavSettingEvent = false
            } else {

                binding.homeLayoutClickOk.visibility = View.GONE
                binding.homeLayout.visibility = View.VISIBLE

            }
            field = value
        }


    var isNavTodoEvent: Boolean = false
        set(value) {


            if (value) {
                binding.todoLayout.visibility = View.GONE
                binding.todoLayoutClickOk.visibility = View.VISIBLE
                isNavHomeEvent = false
                isNavMapEvent = false
                isNavMessageEvent = false
                isNavSettingEvent = false
            } else {
                binding.todoLayout.visibility = View.VISIBLE
                binding.todoLayoutClickOk.visibility = View.GONE
            }
            field = value
        }

    var isNavMapEvent: Boolean = false
        set(value) {


            if (value) {
                binding.mapLayout.visibility = View.GONE
                binding.mapLayoutClickOk.visibility = View.VISIBLE
                isNavTodoEvent = false
                isNavHomeEvent = false
                isNavMessageEvent = false
                isNavSettingEvent = false
            } else {
                binding.mapLayout.visibility = View.VISIBLE
                binding.mapLayoutClickOk.visibility = View.GONE
            }
            field = value
        }


    var isNavMessageEvent: Boolean = false
        set(value) {

            if (value) {
                binding.messageLayout.visibility = View.GONE
                binding.messageLayoutClickOk.visibility = View.VISIBLE
                isNavTodoEvent = false
                isNavMapEvent = false
                isNavHomeEvent = false
                isNavSettingEvent = false
            } else {
                binding.messageLayout.visibility = View.VISIBLE
                binding.messageLayoutClickOk.visibility = View.GONE
            }
            field = value
        }

    var isNavSettingEvent: Boolean = false
        set(value) {

            if (value) {
                binding.settingLayout.visibility = View.GONE
                binding.settingLayoutClickOk.visibility = View.VISIBLE
                isNavTodoEvent = false
                isNavMapEvent = false
                isNavMessageEvent = false
                isNavHomeEvent = false
            } else {

                binding.settingLayout.visibility = View.VISIBLE
                binding.settingLayoutClickOk.visibility = View.GONE
            }
            field = value
        }

}