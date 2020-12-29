package com.lsttsl.study_01.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.lsttsl.study_01.activtiy.MainActivity
import com.lsttsl.study_01.databinding.ActivityLogingPageBinding
import com.lsttsl.study_01.firebase.FirebaseManager
import com.lsttsl.study_01.util.AppConfigure
import com.lsttsl.study_01.util.G

class LogingPageActivity : AppCompatActivity() {

    private val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val databaseReference by lazy { firebaseDatabase.reference }


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


    private var isBoxCheck = false
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

            isBoxCheck = binding.loginCheckBox.isChecked
            
            if (isBoxCheck) {
                AppConfigure.autoLoginCheck = G.loginAuto
            }else{
                AppConfigure.autoLoginCheck = G.loginManual
            }


            AppConfigure.loginUserPw = binding.loginPw.text.toString()
            AppConfigure.loginUserId = binding.loginId.text.toString()

            FirebaseManager(
                databaseReference,
                applicationContext
            ).readUser(binding.loginId.text.toString(), binding.loginPw.text.toString())

        }
    }


    companion object {
        private val TAG = LogingPageActivity::class.simpleName
    }

}