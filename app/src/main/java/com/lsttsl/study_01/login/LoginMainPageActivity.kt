package com.lsttsl.study_01.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lsttsl.study_01.databinding.ActivityLoginPageBinding

class LoginMainPageActivity : AppCompatActivity() {

    //로그인 메인페이지..

    private lateinit var binding: ActivityLoginPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnListener()
    }

    private fun btnListener() {

        binding.loginBtn.setOnClickListener {
            val intent = Intent(applicationContext, LogingPageActivity::class.java)
            startActivity(intent)

        }

        binding.signBtn.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }

    }


    companion object {
        private val TAG = LoginMainPageActivity::class.simpleName
    }


}