package com.lsttsl.study_01.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.lsttsl.study_01.R
import com.lsttsl.study_01.databinding.ActivityLogingPageBinding

class LogingPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnListener()
    }


    private fun btnListener(){
        binding.loginPwSearch.setOnClickListener {
            val intent = Intent(applicationContext, PwSearchActivity::class.java)
            startActivity(intent)
        }

    }



    companion object {
        private val TAG = LogingPageActivity::class.simpleName
    }

}