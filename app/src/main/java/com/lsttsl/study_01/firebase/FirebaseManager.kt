package com.lsttsl.study_01.firebase

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.*
import com.google.firebase.firestore.auth.User
import com.lsttsl.study_01.activtiy.MainActivity

class FirebaseManager(
    private val databaseReference: DatabaseReference,
    private val context: Context
) {

    private val firebaseAccount: String = "account"
    private val USERINFO = "userInfo"
    fun writeNewUser(userId: String, userInfo: UserInfo) {
        databaseReference.child(firebaseAccount).child(USERINFO).child(userId).setValue(userInfo)
            .addOnSuccessListener {
                Toast.makeText(context, "회원 가입이 완료되었습니다...", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "회원가입에 실패하였습니다..", Toast.LENGTH_SHORT).show()
            }


    }

    fun readUser(userId: String, userPw: String) {
        databaseReference.child(firebaseAccount).child(USERINFO).child(userId)
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.getValue(UserInfo::class.java) != null) {
                        val userInfo = snapshot.getValue(UserInfo::class.java)

                        if (userId != userInfo?.userID) {
                            Toast.makeText(context, "아이디가 일치 하지 않습니다......", Toast.LENGTH_SHORT)
                                .show()
                            return
                        }
                        if (userPw != userInfo.userPW) {
                            Toast.makeText(context, "버번이 일치하지 않습니다..", Toast.LENGTH_SHORT).show()
                        }

                        if (userId == userInfo.userID && userPw == userInfo.userPW) {
                            Toast.makeText(context, "로그인이 완료되었습니다...", Toast.LENGTH_SHORT).show()
                            val intent = Intent(context, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(intent)
                        }

                    } else {
                        Toast.makeText(context, "아이디나 비번이 틀렸습니다...", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "onCancelled: ${error.message}")
                }

            })
    }


    fun readGetUserPw(userId: String) {
        databaseReference.child(firebaseAccount).child(USERINFO).child(userId)
            .addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.getValue(UserInfo::class.java) != null) {
                        val userInfo = snapshot.getValue(UserInfo::class.java)

                        if (userId != userInfo?.userID) {
                            Toast.makeText(context, "아이디가 일치 하지 않습니다......", Toast.LENGTH_SHORT)
                                .show()
                            return
                        }

                        Toast.makeText(context, "비번 : ${ userInfo.userPW }", Toast.LENGTH_LONG).show()



                    } else {
                        Toast.makeText(context, "아이디가 없습니다.. 다시한번 확인해주세요", Toast.LENGTH_SHORT).show()

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d(TAG, "onCancelled: ${error.message}")
                }

            })
    }


    companion object {
        private val TAG = FirebaseManager::class.java.simpleName
    }


}