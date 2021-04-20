package com.levit.book_me.core.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.hideKeyboard() {
    val token = windowToken
    val inputService = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputService.hideSoftInputFromWindow(windowToken, 0)
}