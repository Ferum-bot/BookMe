package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.widget.SearchView

class BookMeSearchView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
): SearchView(context, attrs, defStyleAttr) {

    private val searchEditText: EditText = findViewById(androidx.appcompat.R.id.search_src_text)

    @ColorInt
    private val textColor: Int = Color.BLACK

    @ColorInt
    private val hintTextColor: Int = Color.DKGRAY

    init {
        searchEditText.setTextColor(textColor)
        searchEditText.setHintTextColor(hintTextColor)
    }

    fun setTextColor(@ColorRes id: Int) {
        val newColor = context.getColor(id)
        searchEditText.setTextColor(newColor)
    }

    fun setHintTextColor(@ColorRes id: Int) {
        val newColor = context.getColor(id)
        searchEditText.setHintTextColor(newColor)
    }
}