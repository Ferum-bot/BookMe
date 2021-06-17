package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.levit.book_me.R
import com.levit.book_me.core.extensions.dpToPx
import com.levit.book_me.core.models.Genre

class SmallGenreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatTextView(context, attrs, defStyleAttr) {

    companion object {

        private const val TEXT_SIZE_SP = 14f
        private const val TEXT_PADDING_DP = 4

        fun getWithBaseParams(requireContext: () -> Context): SmallGenreView {
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            return SmallGenreView(requireContext()).apply {
                this.layoutParams = layoutParams
                id = View.generateViewId()
            }
        }
    }

    init {
        configureLayout()
    }

    fun setGenre(genre: Genre) {
        text = genre.text
    }

    private fun configureLayout() {
        val backgroundColor = getColor(R.color.light_grey)
        val textColor = getColor(R.color.black)
        val textUnit = TypedValue.COMPLEX_UNIT_SP
        val textPadding = dpToPx(TEXT_PADDING_DP)

        setTextSize(textUnit, TEXT_SIZE_SP)
        setBackgroundColor(backgroundColor)
        setTextColor(textColor)
        setPadding(textPadding, textPadding, textPadding, textPadding)
        gravity = Gravity.CENTER
    }

    private fun getColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(context, id)
    }
}