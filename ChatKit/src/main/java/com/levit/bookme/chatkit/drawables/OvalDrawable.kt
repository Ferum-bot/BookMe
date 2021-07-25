package com.levit.bookme.chatkit.drawables

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import java.lang.Integer.min

internal class OvalDrawable(

    @ColorInt
    private val backgroundColor: Int = Color.WHITE,
    @ColorInt
    private val strokeColor: Int = Color.GRAY,

    @Dimension(unit = Dimension.PX)
    private val strokeWidthPx: Int = 0,

    @Dimension(unit = Dimension.PX)
    private val horizontalMarginPx: Int = 5,
    @Dimension(unit = Dimension.PX)
    private val verticalMarginPx: Int = 0,
): Drawable() {

    private val fillPaint = Paint().apply {
        color = backgroundColor
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val strokePaint = Paint().apply {
        color = strokeColor
        style = Paint.Style.STROKE
        strokeWidth = strokeWidthPx.toFloat()
        isAntiAlias = true
    }

    override fun draw(canvas: Canvas) {
        val left = bounds.left + horizontalMarginPx
        val top = bounds.top + verticalMarginPx
        val right = bounds.right - horizontalMarginPx
        val bottom = bounds.bottom - verticalMarginPx

        val path = getOvalPath(left, top, right, bottom)

        canvas.drawPath(path, fillPaint)
        canvas.drawPath(path, strokePaint)
    }

    override fun setAlpha(alpha: Int) {
        fillPaint.alpha = alpha
        strokePaint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        fillPaint.colorFilter = colorFilter
        strokePaint.colorFilter = colorFilter
    }

    override fun getOpacity() = PixelFormat.TRANSLUCENT

    private fun getOvalPath(left: Int, top: Int, right: Int, bottom: Int): Path {
        val path = Path()

        val xCenter = ((left + right) / 2).toFloat()
        val yCenter = ((top + bottom) / 2).toFloat()
        val radius = min(
            (right - left) / 2, (bottom - top) / 2
        ).toFloat()

        path.addCircle(xCenter, yCenter, radius, Path.Direction.CW)

        return path
    }
}