package com.lsttsl.study_01.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lsttsl.study_01.util.AppConfigure
import com.lsttsl.study_01.databinding.ActivityPwSerachBinding

class PwSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPwSerachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPwSerachBinding.inflate(layoutInflater)
        setContentView(binding.root)
        backEvent()
        getPw()
    }

    private fun backEvent() {
        binding.loginBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getPw() {

        binding.searchBtn.setOnClickListener {


            if (binding.loginUserid.text.toString() == AppConfigure.loginUserId) {
                Toast.makeText(
                    applicationContext,
                    "${AppConfigure.loginUserPw} 비밀번호입니다...",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(applicationContext, "아이디가 틀립니다...", Toast.LENGTH_SHORT).show()

            }


        }
    }

    companion object {
        private val TAG = PwSearchActivity::class.simpleName
    }

}