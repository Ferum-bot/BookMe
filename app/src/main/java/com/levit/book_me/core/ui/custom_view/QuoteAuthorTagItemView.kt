package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.databinding.QuoteAuthorTagItemLayoutBinding

class QuoteAuthorTagItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: QuoteAuthorTagItemLayoutBinding

    private val inflater
    get() = LayoutInflater.from(context)

    init {
        binding = QuoteAuthorTagItemLayoutBinding.inflate(inflater, this, true)
    }

    fun setAuthor(author: QuoteAuthorModel) {
        val count = author.numberOfQuotes
        val resultString = "${author.fullName} ($count)"
        binding.authorTagLabel.text = resultString
    }

    fun setTag(tag: QuoteTagModel) {
        val count = tag.numberOfQuotes
        val resultString = "${tag.name} ($count)"
        binding.authorTagLabel.text = resultString
    }

    fun setNextClickListener(listener: OnClickListener?) {
        binding.chooseButton.setOnClickListener(listener)
    }
}