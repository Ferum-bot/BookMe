package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.databinding.SearchBookItemLayoutBinding

class BigBookItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: SearchBookItemLayoutBinding

    private val inflater
    get() = LayoutInflater.from(context)

    init {
        binding = SearchBookItemLayoutBinding.inflate(inflater, this, true)
    }
}