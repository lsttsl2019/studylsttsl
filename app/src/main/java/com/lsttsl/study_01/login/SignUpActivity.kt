package com.lsttsl.study_01.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.lsttsl.study_01.util.AppConfigure
import com.lsttsl.study_01.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {


    private lateinit var  binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        backEvent()
        signUpCreateEvent()
    }

    private fun backEvent(){
        binding.signBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun signUpCreateEvent(){
        binding.signCreateBtn.setOnClickListener {

            if (binding.signId.text.toString().isEmpty() ){
                return@setOnClickListener Toast.makeText(applicationContext, "아이디 입력해주세요", Toast.LENGTH_SHORT).show()
            }

            if (binding.signPw.text.isEmpty()) {
                return@setOnClickListener Toast.makeText(applicationContext, "비번을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            if (binding.signPwConfirm.text.toString().isEmpty()) {
                return@setOnClickListener Toast.makeText(applicationContext, "Confirm비번을 입력해주세요",Toast.LENGTH_SHORT).show()
            }

            if (binding.signPw.text.toString() != binding.signPwConfirm.text.toString()){
                Toast.makeText(applicationContext, "입력된 두 비밀번호가 틀립니다..." ,Toast.LENGTH_SHORT).show()
                return@setOnClickListener 
            }else {
                Log.d(TAG, "signUpCreateEvent: 둘다 같음")

                //TODO 네트워크 연결 -> 아이디
                AppConfigure.loginUserId = binding.signId.text.toString()
                AppConfigure.loginUserPw = binding.signPw.text.toString()


                Toast.makeText(applicationContext, "아이디가 생성되었습니다...", Toast.LENGTH_SHORT).show()
                finish()
            }





        }

    }



    companion object{
        private val TAG = SignUpActivity::class.simpleName

    }



}