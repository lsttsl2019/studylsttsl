package com.lsttsl.study_01.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "login")
data class LoginEntity(
    @ColumnInfo(name = "userId", defaultValue = "") var userId: String,
    @ColumnInfo(name = "userPw", defaultValue = "") var userPw: String,
    @ColumnInfo(name = "last_date", defaultValue = "") var last_date :String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}