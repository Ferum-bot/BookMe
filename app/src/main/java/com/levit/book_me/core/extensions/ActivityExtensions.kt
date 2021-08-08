package com.levit.book_me.core.extensions

import androidx.appcompat.app.AppCompatActivity
import com.levit.book_me.core_base.extensions.setTextToClipboard

fun AppCompatActivity.setTextToClipboard(title: String = "", getText: () -> String) {
    val context = baseContext
    context.setTextToClipboard(title, getText)
}