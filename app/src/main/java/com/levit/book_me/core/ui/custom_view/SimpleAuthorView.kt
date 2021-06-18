package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.core.extensions.dpToPx
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.SimpleAuthorsItemLayoutBinding
import java.util.zip.Inflater

class SimpleAuthorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {

        private const val LEFT_MARGIN_DP = 4
        private const val RIGHT_MARGIN_DP = 4
        private const val TOP_MARGIN_DP = 8
        private const val BOTTOM_MARGIN_DP = 0

        fun getWithBaseParams(requireContext: () -> Context): SimpleAuthorView {
            val context = requireContext.invoke()
            val leftMargin = context.dpToPx(LEFT_MARGIN_DP)
            val rightMargin = context.dpToPx(RIGHT_MARGIN_DP)
            val topMargin = context.dpToPx(TOP_MARGIN_DP)
            val bottomMargin = context.dpToPx(BOTTOM_MARGIN_DP)

            val view = SimpleAuthorView(context)
            val layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT,
            ).apply {
                setMargins(leftMargin, topMargin, rightMargin, bottomMargin)
            }
            view.layoutParams = layoutParams
            view.id = View.generateViewId()

            return  view
        }
    }

    private val inflater: LayoutInflater
    get() = LayoutInflater.from(context)

    private val binding: SimpleAuthorsItemLayoutBinding

    init {

        binding = SimpleAuthorsItemLayoutBinding.inflate(inflater, this, true)
    }

    fun setAuthor(author: Author) {
        binding.fullName.text = author.fullName
    }
}