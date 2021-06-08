package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.R
import com.levit.book_me.databinding.QuoteTypeChooseLayoutBinding

class QuoteTypeChooseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    interface OnTypeClickListener {

        fun onTagsClicked()

        fun onAuthorsClicked()
    }

    private val binding: QuoteTypeChooseLayoutBinding

    private val inflater
    get() = LayoutInflater.from(context)

    private var typeListener: OnTypeClickListener? = null

    init {
        binding = QuoteTypeChooseLayoutBinding.inflate(inflater, this, true)

        setAllClickListeners()
    }

    fun setNumberOfTags(count: Int) {
        val label = getString(R.string.choose_from_tags, count)
        binding.tagLabel.text = label
    }

    fun setNumberOfAuthors(count: Int) {
        val label = getString(R.string.choose_from_authors, count)
        binding.authorLabel.text = label
    }

    fun setTypeListener(listener: OnTypeClickListener?) {
        typeListener = listener
    }

    private fun setAllClickListeners() {
        binding.authorNextButton.setOnClickListener {
            typeListener?.onAuthorsClicked()
        }

        binding.tagNextButton.setOnClickListener {
            typeListener?.onTagsClicked()
        }
    }

    private fun getString(@StringRes id: Int, count: Int) =
        context.getString(id, count.toString())
}