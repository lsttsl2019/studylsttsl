package com.lsttsl.study_01.activtiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        createToolBar()
        baseFragment()
        bottomNavViewEvent()

    }

    private fun createToolBar() {

        setSupportActionBar(binding.toolbar)
        actionBar?.setDisplayShowCustomEnabled(true)
       actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.title = "HOME"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
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


    private fun bottomNavViewEvent() {
        binding.mainBottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {

                    createFragment(HomeFragment.instance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_map -> {

                    createFragment(MapFragment.instance())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_message -> {

                    createFragment(MessageFragment.instance())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_todo -> {

                    createFragment(TodoFragment.instance())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.nav_setting -> {

                    createFragment(SettingFragment.instance())
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false
            }
        }
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }


}