package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.levit.book_me.R
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.databinding.QuoteLayoutItemBinding
import kotlinx.android.synthetic.main.quote_layout_item.view.*

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

    fun setChosen(chosen: Boolean) = if (chosen) {
        binding.backGround.setBackgroundResource(R.drawable.bg_for_quote_layout_chosen_item)
    } else {
        binding.backGround.setBackgroundResource(R.drawable.bg_for_quote_layout_not_chosen_item)
    }

    fun hideAuthor(hide: Boolean) {
        binding.author.isVisible = !hide
    }

    fun setNotChosenText() {
        text.text = getString(R.string.more_than_500_favourite_quotes_from_all_over_the_world)
    }

    private fun getString(@StringRes id: Int)
        = context.getString(id)
}