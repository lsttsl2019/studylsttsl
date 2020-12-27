package com.lsttsl.study_01.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lsttsl.study_01.activtiy.MainActivity
import com.lsttsl.study_01.databinding.ActivityLogingPageBinding
import com.lsttsl.study_01.util.AppConfigure
import com.lsttsl.study_01.util.G

class LogingPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogingPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnListener()
        loginChecking()
    }


    private fun btnListener() {
        binding.loginPwSearch.setOnClickListener {
            val intent = Intent(applicationContext, PwSearchActivity::class.java)
            startActivity(intent)
        }

        binding.loginBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun loginChecking() {

        binding.loginBtn.setOnClickListener {
            if (binding.loginId.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    "아이디를 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (binding.loginPw.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    "비밀번호를 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            }




            if (AppConfigure.loginUserId == binding.loginId.text.toString() && AppConfigure.loginUserPw == binding.loginPw.text.toString()) {

                val intent = Intent(applicationContext, MainActivity::class.java)
                G.loginCheck = true
                startActivity(intent)

            } else {
                Toast.makeText(applicationContext, "아이디 또는 비번이 잘못되었습니다..", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }


    companion object {
        private val TAG = LogingPageActivity::class.simpleName
    }

}