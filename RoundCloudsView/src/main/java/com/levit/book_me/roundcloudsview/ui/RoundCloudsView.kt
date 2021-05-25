package com.levit.book_me.roundcloudsview.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import com.levit.book_me.roundcloudsview.R
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.extensions.toRoundCloudModels
import com.levit.book_me.roundcloudsview.core.interfaces.RoundCloudStateChangeListener
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.core.utills.RoundCloudsViewAttrs

class RoundCloudsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): View(context, attrs, defStyleAttr) {

    @ColorInt
    private var checkedCloudColor: Int = Color.BLACK
    @ColorInt
    private var notCheckedCloudColor: Int = Color.WHITE

    @ColorInt
    private var checkedTextColor: Int = Color.WHITE
    @ColorInt
    private var notCheckedTextColor: Int = Color.BLACK

    private var cloudModels: List<RoundCloudModel> = listOf()

    private var cloudListener: RoundCloudStateChangeListener? = null

    init {
        initPropertiesWithAttrs(attrs)
    }

    fun setCheckedCloudColor(@ColorInt rgbColor: Int) {
        checkedCloudColor = rgbColor
        invalidate()
    }

    fun setNotCheckedCloudColor(@ColorInt rgbColor: Int) {
        notCheckedCloudColor = rgbColor
        invalidate()
    }

    fun setCheckedTextColor(@ColorInt rgbColor: Int) {
        checkedTextColor = rgbColor
        invalidate()
    }

    fun setNotCheckedTextColor(@ColorInt rgbColor: Int) {
        notCheckedTextColor = rgbColor
        invalidate()
    }

    fun setClouds(clouds: List<RoundCloud>) {
        cloudModels = clouds.toRoundCloudModels()
    }

    fun setCloudStateChangeListener(listener: RoundCloudStateChangeListener?) {
        cloudListener = listener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        return
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        return
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return performClick()
    }

    override fun performClick(): Boolean {
        val result = super.performClick()
        return false
    }

    private fun initPropertiesWithAttrs(attrs: AttributeSet?) {
        attrs ?: return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundCloudsView)

        with(typedArray) {
            checkedCloudColor = getColor(
                RoundCloudsViewAttrs.CHECKED_CLOUD_COLOR,
                Color.BLACK,
            )
            notCheckedCloudColor = getColor(
                RoundCloudsViewAttrs.NOT_CHECKED_CLOUD_COLOR,
                Color.WHITE,
            )

            checkedTextColor = getColor(
                RoundCloudsViewAttrs.CHECKED_TEXT_COLOR,
                Color.WHITE,
            )
            notCheckedTextColor = getColor(
                RoundCloudsViewAttrs.NOT_CHECKED_TEXT_COLOR,
                Color.BLACK
            )
        }

        typedArray.recycle()
    }
}