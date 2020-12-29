package com.lsttsl.study_01.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.lsttsl.study_01.util.AppConfigure
import com.lsttsl.study_01.databinding.ActivitySignUpBinding
import com.lsttsl.study_01.firebase.FirebaseManager
import com.lsttsl.study_01.firebase.UserInfo

class SignUpActivity : AppCompatActivity() {

    private val firebaseDatabase by lazy { FirebaseDatabase.getInstance() }
    private val databaseReference by lazy { firebaseDatabase.reference }


    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        backEvent()
        signUpCreateEvent()
    }

    private fun backEvent() {
        binding.signBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun signUpCreateEvent() {
        binding.signCreateBtn.setOnClickListener {

            if (binding.signId.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    "아이디 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (binding.signPw.text.isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    "비번을 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (binding.signPwConfirm.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(
                    applicationContext,
                    "Confirm비번을 입력해주세요",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (binding.signPw.text.toString() != binding.signPwConfirm.text.toString()) {
                Toast.makeText(applicationContext, "입력된 두 비밀번호가 틀립니다...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                Log.d(TAG, "signUpCreateEvent: 둘다 같음")

                //TODO 네트워크 연결 -> 아이디
                AppConfigure.loginUserId = binding.signId.text.toString()
                AppConfigure.loginUserPw = binding.signPw.text.toString()

                val userList = UserInfo(AppConfigure.loginUserId, AppConfigure.loginUserPw)

                FirebaseManager(
                    databaseReference,
                    applicationContext
                ).writeNewUser(
                    binding.signId.text.toString(),
                    userList
                )

                finish()

            }


        }

    }


    companion object {
        private val TAG = SignUpActivity::class.simpleName

    }


}