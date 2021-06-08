package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.databinding.SearchViewPlaceholderLayoutBinding

class SearchPlaceholderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: SearchViewPlaceholderLayoutBinding

    private val inflater: LayoutInflater
    get() = LayoutInflater.from(context)

    init {
        binding = SearchViewPlaceholderLayoutBinding.inflate(inflater, this, true)
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        binding.searchHint.setOnClickListener(listener)
        binding.searchIcon.setOnClickListener(listener)
    }
}