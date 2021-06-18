package com.levit.book_me.core.extensions

import android.content.Context

fun Context.dpToPx(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (dp.toFloat() *  density + 0.5f).toInt()
}