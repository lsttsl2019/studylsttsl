package com.lsttsl.study_01.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.lsttsl.study_01.util.AppConfigure
import com.lsttsl.study_01.databinding.ActivityPwSerachBinding
import com.lsttsl.study_01.firebase.FirebaseManager

class PwSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPwSerachBinding
    private val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val databaseReference by lazy { firebaseDatabase.reference }


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
            FirebaseManager(
                databaseReference,
                applicationContext
            ).readGetUserPw(binding.loginUserid.text.toString())

        }
    }

    companion object {
        private val TAG = PwSearchActivity::class.simpleName
    }

}