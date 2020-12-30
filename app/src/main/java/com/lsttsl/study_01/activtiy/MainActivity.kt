package com.lsttsl.study_01.activtiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lsttsl.study_01.R
import com.lsttsl.study_01.databinding.ActivityMainBinding
import com.lsttsl.study_01.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        baseFragment()
        createToolBar()
        onBtnEvent()
    }


    private fun onBtnEvent() {
        binding.homeLayout.setOnClickListener(onClickListener)
        binding.todoLayout.setOnClickListener(onClickListener)
        binding.mapLayout.setOnClickListener(onClickListener)
        binding.messageLayout.setOnClickListener(onClickListener)
        binding.settingLayout.setOnClickListener(onClickListener)
        binding.homePopUp.setOnClickListener(onClickListener)
        binding.toolbarBack.setOnClickListener(onClickListener)


    }

    private val onClickListener = View.OnClickListener { view ->
        when (view) {
            binding.homeLayout -> {
                val fragment = HomeFragment.instance()
                createFragment(fragment)
            }

            binding.todoLayout -> {
                val fragment = TodoFragment.instance()
                createFragment(fragment)
            }

            binding.mapLayout -> {
                val fragment = MapFragment.instance()
                createFragment(fragment)

            }
            binding.messageLayout -> {
                val fragment = MessageFragment.instance()
                createFragment(fragment)
            }

            binding.settingLayout -> {
                val fragment = SettingFragment.instance()
                createFragment(fragment)
            }

            binding.homePopUp -> {
                val popup = PopupMenu(this, view, Gravity.CENTER)
                val inflater = popup.menuInflater
                val menu = popup.menu
                inflater.inflate(R.menu.menu_toolbar, menu)
                popup.show()
                popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.top_new -> {
                            //  최신 업로드된.. or 데이터상 날짜순
                            Log.d(TAG, "onOptionsItemSelected: new")

                        }
                        R.id.top_old -> {
                            // 가장 오래 업로드 .. 데이터상 날짜순..
                            Log.d(TAG, "onOptionsItemSelected: old")

                        }
                    }
                    return@OnMenuItemClickListener false
                })

            }

            binding.toolbarBack -> {
                finishAffinity()
            }

        }
    }


    private fun createToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.toolbarTitle.text = "나의 여행기"


    }


    private fun baseFragment() {
        val fragment = HomeFragment.instance()

        createFragment(fragment)

    }

    private fun createFragment(fragment: Fragment) {
        binding.baseFragment.removeAllViewsInLayout()
        supportFragmentManager.beginTransaction().replace(binding.baseFragment.id, fragment)
            .commitAllowingStateLoss()

    }


    companion object {
        private val TAG = MainActivity::class.simpleName
    }


}