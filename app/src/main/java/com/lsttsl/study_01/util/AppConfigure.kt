package com.lsttsl.study_01.util

import android.content.Context
import android.content.SharedPreferences

object AppConfigure {

    private lateinit var context: Context
    private lateinit var preferences: SharedPreferences


    private enum class Keys {
        UserId,
        UserPw
    }


    @Synchronized
    fun initialize(context: Context) {
        AppConfigure.context = context
        val prefsKey = context.packageName.toString() + ".ShardDataControl"
        preferences = context.getSharedPreferences(prefsKey, Context.MODE_PRIVATE)
    }

    var loginUserId: String
        get() = getString(Keys.UserId.name, "") ?: ""
        set(value) = setString(Keys.UserId.name, value)

    var loginUserPw: String
        get() = getString(Keys.UserPw.name, "") ?: ""
        set(value) = setString(Keys.UserPw.name, value)

    private fun setString(keyName: String, value: String?) =
        preferences.edit().putString(keyName, value).apply()

    private fun getString(keyName: String, defaultValue: String?): String? =
        preferences.getString(keyName, defaultValue)

}