package com.lsttsl.study_01.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserInfo(
    var userID : String? = "",
    var userPW : String? = ""
)