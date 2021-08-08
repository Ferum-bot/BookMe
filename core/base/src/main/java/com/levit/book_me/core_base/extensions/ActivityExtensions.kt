package com.levit.book_me.core_base.extensions

import android.app.Activity

fun Activity.setTextToClipboard(title: String = "", getText: () -> String) {
    val context = applicationContext
    context.setTextToClipboard(title, getText)
}