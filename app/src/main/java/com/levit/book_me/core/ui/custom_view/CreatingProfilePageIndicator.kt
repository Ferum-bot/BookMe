package com.levit.book_me.core.ui.custom_view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.databinding.CreatingProfilePageIndicatorLayoutBinding

class CreatingProfilePageIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    interface OnActivePrefixNumberChangeListener {
        fun activePrefixChanged(activePrefixNumber: Int)
    }

    private val binding: CreatingProfilePageIndicatorLayoutBinding

    private var activeColor: Int = Color.BLACK
    private var inactiveColor: Int = Color.rgb(220, 220, 220)

    private lateinit var listOfViews: List<View>

    init {
        val inflater = LayoutInflater.from(context)
        binding = CreatingProfilePageIndicatorLayoutBinding
            .inflate(inflater, this, true)

        configureLayout()
    }

    fun setActiveColor(@ColorInt color: Int) {
        activeColor = color
    }

    fun setInactiveColor(@ColorInt color: Int) {
        inactiveColor = color
    }

    /**
     * Number must be between 0 to [listOfViews.size()]
     */
    fun setActivePrefixNumber(activePrefixNumber: Int) {
        if (activePrefixNumberIsNotValid(activePrefixNumber)) {
            return
        }
        for (activePosOfIndicator in 0 until activePrefixNumber) {
            listOfViews[activePosOfIndicator].setBackgroundColor(activeColor)
        }
        for (inactivePosOfIndicator in activePrefixNumber until listOfViews.size) {
            listOfViews[inactivePosOfIndicator].setBackgroundColor(inactiveColor)
        }
    }

    private fun configureLayout() {
        listOfViews = listOf(
            binding.firstIndicator, binding.secondIndicator,
            binding.thirdIndicator, binding.foursIndicator,
            binding.fivesIndicator, binding.sixesIndicator,
        )

        setActivePrefixNumber(0)
    }

    private fun activePrefixNumberIsNotValid(activePrefixNumber: Int)
    = activePrefixNumber !in 0..listOfViews.size
}