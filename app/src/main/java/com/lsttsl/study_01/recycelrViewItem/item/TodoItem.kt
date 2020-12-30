package com.lsttsl.study_01.recycelrViewItem.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoItem(
    var title: String?,
    var day: String?,
    var img: Int,
) : Parcelable