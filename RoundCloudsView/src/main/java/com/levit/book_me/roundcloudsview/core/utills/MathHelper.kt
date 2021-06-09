package com.levit.book_me.roundcloudsview.core.utills

import android.graphics.PointF
import kotlin.math.sqrt

internal object MathHelper {

    const val PI = Math.PI
    const val ANGLE_45_RAD = PI / 4f

    private const val EPS = 1e-5

    fun circleContainsPoint(circleCenterPx: PointF, radiusPx: Int, pointPx: PointF): Boolean {
        val distance = distanceBetween(circleCenterPx, pointPx)
        return distance.isLess(radiusPx.toDouble())
    }

    fun circlesIntercept(firstCenter: PointF, firstRadius: Int, secondCenter: PointF, secondRadius: Int): Boolean {
        val distanceBetweenCenters = distanceBetween(firstCenter, secondCenter)
        val minAvailableDistance = firstRadius + secondRadius
        return distanceBetweenCenters.isLess(minAvailableDistance.toDouble())
    }

    fun distanceBetween(startPoint: PointF, endPoint: PointF): Double {
        val startX = startPoint.x.toDouble()
        val startY = startPoint.y.toDouble()
        val endX = endPoint.x.toDouble()
        val endY = endPoint.y.toDouble()

        val firstValue = (startX - endX) * (startX - endX)
        val secondValue = (startY - endY) * (startY - endY)

        return sqrt(firstValue + secondValue)
    }

    private fun Double.isLess(another: Double): Boolean {
        val rawValue = this - another
        return rawValue + EPS < 0
    }
}