package com.lsttsl.study_01.util

import android.content.res.Resources
import kotlin.math.roundToInt

fun Int.toTimeString(zeorexist: Boolean): String {

    var time: String = "00:00"

    val hh = this / (60 * 60)
    val mm = this / 60 - hh * 60
    val ss = this - (hh * 60 * 60 + mm * 60)

    val hhString = hh.toString()

    var mmString = mm.toString()
    if (mmString.length < 2) {
        mmString = "0$mmString"
    }

    var ssString = ss.toString()
    if (ssString.length < 2) {
        ssString = "0$ssString"
    }
    time = if (zeorexist && hhString.compareTo("0") == 0) {
        "$mmString:$ssString"
    } else {
        "$hhString$mmString:$ssString"
    }
    return time

}


fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).roundToInt()
}
