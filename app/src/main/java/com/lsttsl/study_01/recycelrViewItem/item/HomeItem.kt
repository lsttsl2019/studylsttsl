package com.lsttsl.study_01.recycelrViewItem.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


//원래는 이미지 주소를 주어야하지만.. 지금은 드로어블에서 주도록하자..

@Parcelize
data class HomeItem(
    var title: String?,
    var day : String,
    var img: Int
): Parcelable