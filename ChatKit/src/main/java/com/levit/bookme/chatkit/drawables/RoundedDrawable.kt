package com.levit.bookme.chatkit.drawables

import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.Dimension

internal class RoundedDrawable(

    @ColorInt
    private val backgroundColor: Int = Color.GRAY,
    @ColorInt
    private val strokeColor: Int = backgroundColor,

    @Dimension
    private val strokeWidthPx: Int = 0,

    @Dimension
    private val radiusTopLeftPx: Int = 10,
    @Dimension
    private val radiusTopRightPx: Int = 10,
    @Dimension
    private val radiusBottomLeftPx: Int = 5,
    @Dimension
    private val radiusBottomRightPx: Int = 5,
): Drawable() {

    private val fillPaint = Paint().apply {
        color = backgroundColor
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val strokePaint = Paint().apply {
        color = strokeColor
        strokeWidth = strokeWidthPx.toFloat()
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    override fun draw(canvas: Canvas) {
        val left = bounds.left
        val top = bounds.top
        val right = bounds.right
        val bottom = bounds.bottom

        val path = getRoundedCornersPath(left, top, right, bottom)

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

    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT

    /**
     * First go around clockwise and calculating lines coordinates.
     * After calculating control points for bezier curves.
     * Than using the coordinates and control points, we build lines and bezier curves.
     */
    private fun getRoundedCornersPath(left: Int, top: Int, right: Int, bottom: Int): Path {
        val path = Path()

        val startTopLineX = (left + radiusTopLeftPx).toFloat()
        val startTopLineY = top.toFloat()
        val endTopLineX = (right - radiusTopRightPx).toFloat()
        val endTopLineY = top.toFloat()

        val startRightLineX = right.toFloat()
        val startRightLineY = (top + radiusTopRightPx).toFloat()
        val endRightLineX = right.toFloat()
        val endRightLineY = (bottom - radiusBottomRightPx).toFloat()

        val startBottomLineX = (right - radiusBottomRightPx).toFloat()
        val startBottomLineY = bottom.toFloat()
        val endBottomLineX = (left + radiusBottomLeftPx).toFloat()
        val endBottomLineY = bottom.toFloat()

        val startLeftLineX = left.toFloat()
        val startLeftLineY = (bottom - radiusBottomLeftPx).toFloat()
        val endLeftLineX = left.toFloat()
        val endLeftLineY = (top + radiusTopLeftPx).toFloat()

        val topLeftControlPointX = left.toFloat()
        val topLeftControlPointY = top.toFloat()

        val topRightControlPointX = right.toFloat()
        val topRightControlPointY = top.toFloat()

        val bottomRightControlPointX = right.toFloat()
        val bottomRightControlPointY = bottom.toFloat()

        val bottomLeftControlPointX = left.toFloat()
        val bottomLeftControlPointY = bottom.toFloat()

        path.moveTo(startTopLineX, startTopLineY)
        path.lineTo(endTopLineX, endTopLineY)
        path.quadTo(topRightControlPointX, topRightControlPointY, startRightLineX, startRightLineY)
        path.lineTo(endRightLineX, endRightLineY)
        path.quadTo(bottomRightControlPointX, bottomRightControlPointY, startBottomLineX, startBottomLineY)
        path.lineTo(endBottomLineX, endBottomLineY)
        path.quadTo(bottomLeftControlPointX, bottomLeftControlPointY, startLeftLineX, startLeftLineY)
        path.lineTo(endLeftLineX, endLeftLineY)
        path.quadTo(topLeftControlPointX, topLeftControlPointY, startTopLineX, startTopLineY)

        path.close()
        return path
    }
}