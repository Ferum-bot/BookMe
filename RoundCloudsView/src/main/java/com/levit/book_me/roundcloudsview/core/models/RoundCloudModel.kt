package com.levit.book_me.roundcloudsview.core.models

import android.graphics.PointF
import android.os.Parcelable
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.utills.MathHelper
import com.levit.book_me.roundcloudsview.core.utills.RoundCloudsViewConstants
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class RoundCloudModel(

    val cloud: RoundCloud,

    var state: RoundCloudState = RoundCloudState.NOT_CHECKED,

    /**
     * The cloud text model. Needed to calculate text start coordinates
     * personal for each cloud model.
     * This text model also needed to support multiline text drawing on
     * canvas. Every value of list contains information about new text line.
     * Use the @CloudTextCoordinateCalculator
     * to calculate valid value.
     */
    var textModels: List<CloudTextModel> = listOf(),

    /**
     * The circle radius in pixels. Use the requested radius from
     * @CloudModelSizeHolder to get correct behavior of cloud view.
     */
    val radiusPx: Int = 0,

    /**
     * The circle x coordinate offset relatively RoundCloudsView
     * @center x coordinate.
     */
    val xOffsetPx: Int = 0,

    /**
     * The circle y coordinate offset relatively RoundCloudsView
     * @center y coordinate.
     */
    val yOffsetPx: Int = 0,
): Parcelable {

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
        val realXClickCoordinate = pointPx.x.toInt() - currentXOffsetPx
        val realYClickCoordinate = pointPx.y.toInt()
        val clickPoint = PointF(realXClickCoordinate.toFloat(), realYClickCoordinate.toFloat())

        val currentCloudXCoordinate = xOffsetPx + viewCenterPx.x.toInt()
        val currentCloudYCoordinate = yOffsetPx + viewCenterPx.y.toInt()
        val actualCloudPoint = PointF(currentCloudXCoordinate.toFloat(), currentCloudYCoordinate.toFloat())

        return MathHelper.circleContainsPoint(actualCloudPoint, radiusPx, clickPoint)
    }

    fun isVisibleWith(newXOffset: Int, viewCenterPx: PointF): Boolean {
        val realXCloudCoordinate = viewCenterPx.x.toInt() + xOffsetPx
        val realXViewCoordinate =
        if (newXOffset >= 0) {
            viewCenterPx.x.toInt() + newXOffset - RoundCloudsViewConstants.ADDITIONAL_SCROLL_AVAILABLE_HORIZONTAL_DISTANCE
        } else {
            viewCenterPx.x.toInt() + newXOffset + RoundCloudsViewConstants.ADDITIONAL_SCROLL_AVAILABLE_HORIZONTAL_DISTANCE
        }

        val xMaxAvailableCoordinate = realXViewCoordinate + viewCenterPx.x.toInt()
        val xMinAvailableCoordinate = realXViewCoordinate - viewCenterPx.x.toInt()

        if (realXCloudCoordinate + radiusPx <= xMaxAvailableCoordinate && newXOffset >= 0) {
            return true
        }
        if (realXCloudCoordinate - radiusPx >= xMinAvailableCoordinate && newXOffset < 0) {
            return true
        }
        return false
    }

    fun getWithNewOffsets(newXOffset: Int? = null, newYOffset: Int? = null): RoundCloudModel {
        val actualXOffset = newXOffset ?: xOffsetPx
        val actualYOffset = newYOffset ?: yOffsetPx

        return RoundCloudModel(
            cloud = cloud,
            state = state,
            textModels = textModels,
            radiusPx = radiusPx,
            xOffsetPx = actualXOffset,
            yOffsetPx = actualYOffset,
        )
    }
}