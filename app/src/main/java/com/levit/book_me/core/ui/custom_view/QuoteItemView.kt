package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.databinding.QuoteLayoutItemBinding

class QuoteItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: QuoteLayoutItemBinding

    private val inflater
    get() = LayoutInflater.from(context)

    init {
        binding = QuoteLayoutItemBinding.inflate(inflater, this, true)
    }

    fun setQuote(quote: GoQuote) = with(binding) {
        text.text = quote.text
        author.text = quote.authorFullName
    }
}