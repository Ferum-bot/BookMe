package com.levit.book_me.core.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.levit.book_me.R


private const val CROSS_FADE_DURATION = 1000

fun View.hideKeyboard() {
    val token = windowToken
    val inputService = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputService.hideSoftInputFromWindow(windowToken, 0)
}

fun View.defaultGlideOptions() = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.default_image_placeholder)
        .error(R.drawable.ic_default_error_placeholder)
        .fallback(R.drawable.ic_book_me_icon)

fun View.noCacheGlideOptions() = RequestOptions()
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .placeholder(R.drawable.default_image_placeholder)
    .error(R.drawable.ic_default_error_placeholder)
    .fallback(R.drawable.ic_book_me_icon)

fun View.dpToPx(dp: Int): Int {
    val density = resources.displayMetrics.density
    return (dp.toFloat() *  density + 0.5f).toInt()
}
