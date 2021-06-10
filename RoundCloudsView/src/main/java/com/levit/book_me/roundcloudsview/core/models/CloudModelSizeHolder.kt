package com.levit.book_me.roundcloudsview.core.models

import com.levit.book_me.roundcloudsview.core.utills.RoundCloudsViewConstants
import java.lang.Integer.max

/**
 * This class holds all data size about specific
 * cloud model.
 */
internal data class CloudModelSizeHolder(

    val viewCenterXCoordinatePx: Int = 0,
    val viewCenterYCoordinatePx: Int = 0,

    /**
     * The requested diameter of large cloud view.
     */
    val largeCloudSizePx: Int = 0,

    /**
     * The requested diameter of small cloud view.
     */
    val smallCloudSizePx: Int = 0,

    /**
     * The margin between clouds in pixels.
     */
    val cloudMarginPx: Int = 0,
) {

    companion object {

        fun getFromViewSize(viewWidthPx: Int, viewHeightPx: Int, cloudMarginPx: Int? = null): CloudModelSizeHolder {
            val centerX = viewWidthPx / 2
            val centerY = viewHeightPx / 2

            val cloudMargin =
                cloudMarginPx ?: max(viewHeightPx, viewWidthPx) / RoundCloudsViewConstants.RELATIVELY_CLOUD_MARGIN

            var largeCloudSize = (viewHeightPx + viewWidthPx) / 6
            largeCloudSize -= cloudMargin + RoundCloudsViewConstants.ADDITIONAL_CLOUD_MARGIN
            var smallCloudSize = viewWidthPx / RoundCloudsViewConstants.RELATIVELY_SMALL_CLOUD_SIZE
            smallCloudSize -= cloudMargin + RoundCloudsViewConstants.ADDITIONAL_CLOUD_MARGIN

            return CloudModelSizeHolder(
                viewCenterXCoordinatePx = centerX,
                viewCenterYCoordinatePx = centerY,
                largeCloudSizePx = largeCloudSize,
                smallCloudSizePx = smallCloudSize,
                cloudMarginPx = cloudMargin,
            )
        }
    }

    val largeCloudRadiusPx: Int
    get() = largeCloudSizePx / 2

    val smallCloudRadiusPx: Int
    get() = smallCloudSizePx / 2
}
