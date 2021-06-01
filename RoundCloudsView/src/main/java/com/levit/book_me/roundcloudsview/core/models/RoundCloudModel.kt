package com.levit.book_me.roundcloudsview.core.models

import android.graphics.PointF
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.utills.MathHelper

internal class RoundCloudModel(

    val cloud: RoundCloud,

    var state: RoundCloudState = RoundCloudState.NOT_CHECKED,

    /**
     * The circle radius in pixels. Use the requested radius from
     * @CloudModelSizeHolder to get correct behavior of cloud view.
     */
    val radiusPx: Int = 0,

    /**
     * The circle x coordinate offset relatively RoundCloudsView
     * center x coordinate.
     */
    val xOffsetPx: Int = 0,

    /**
     * The circle y coordinate offset relatively RoundCloudsView
     * center y coordinate.
     */
    val yOffsetPx: Int = 0,

) {

    val text: String
    get() = cloud.text

    val size: RoundCloudSize
    get() = cloud.size

    fun getXCenterCoordinatePx(viewCenterXPx: Int): Int {
        return viewCenterXPx + xOffsetPx
    }

    fun getYCenterCoordinatePx(viewCenterYPx: Int): Int {
        return viewCenterYPx + yOffsetPx
    }

    fun containsPoint(pointPx: PointF, viewCenterPx: PointF, currentXOffsetPx: Int): Boolean {
        val realXClickCoordinate = pointPx.x.toInt() + currentXOffsetPx
        val realYClickCoordinate = pointPx.y.toInt()
        val clickPoint = PointF(realXClickCoordinate.toFloat(), realYClickCoordinate.toFloat())

        val currentCloudXCoordinate = xOffsetPx + viewCenterPx.x.toInt()
        val currentCloudYCoordinate = yOffsetPx + viewCenterPx.y.toInt()
        val actualCloudPoint = PointF(currentCloudXCoordinate.toFloat(), currentCloudYCoordinate.toFloat())

        return MathHelper.circleContainsPoint(actualCloudPoint, radiusPx, clickPoint)
    }
}