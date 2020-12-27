package com.lsttsl.study_01.activtiy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.lsttsl.study_01.R
import com.lsttsl.study_01.login.LoginMainPageActivity

class IntroActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        // 추후 로그인 되어있으면 --> 바로 메인화면으로 넘겨준다~
        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(applicationContext, LoginMainPageActivity::class.java)
            startActivity(intent)
            finish()

        }, NEXT_TIME)

    }


    companion object {
        private const val NEXT_TIME = 3000L

    }
}