package com.levit.book_me.core_base.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun Context.setTextToClipboard(title: String = "", getText: () -> String) {
    val text = getText.invoke()
    val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(title, text)
    clipboardManager.setPrimaryClip(clip)
}