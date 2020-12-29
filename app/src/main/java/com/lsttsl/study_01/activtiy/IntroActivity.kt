package com.lsttsl.study_01.activtiy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.lsttsl.study_01.R
import com.lsttsl.study_01.login.LoginMainPageActivity
import com.lsttsl.study_01.login.LogingPageActivity
import com.lsttsl.study_01.util.AppConfigure
import com.lsttsl.study_01.util.G

class IntroActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        autoLoginCheck()
        Log.d(TAG, "onCreate: ${AppConfigure.autoLoginCheck}")
    }

    private fun autoLoginCheck() {
        if (AppConfigure.autoLoginCheck == G.loginAuto) {

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()

            }, NEXT_TIME)


        } else {

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(applicationContext, LoginMainPageActivity::class.java)
                startActivity(intent)
                finish()
            }, NEXT_TIME)
        }

    }


    companion object {
        private const val NEXT_TIME = 3000L
        private val TAG = IntroActivity::class.java.simpleName
    }
}