package com.levit.book_me.roundcloudsview.core.models

import android.content.Context
import com.levit.book_me.roundcloudsview.core.utills.RoundCloudsViewConstants

/**
 * This class holds all data size about specific
 * cloud model.
 */
internal data class CloudModelSizeHolder(

    val viewCenterXCoordinateDp: Int = 0,
    val viewCenterYCoordinateDp: Int = 0,

    val largeCloudSizeDp: Int = 0,

    val smallCloudSizeDp: Int = 0,
) {

    companion object {

        fun getFromViewSize(viewWidthDp: Int, viewHeightDp: Int): CloudModelSizeHolder {
            val centerX = viewWidthDp / 2
            val centerY = viewHeightDp / 2

            val largeCloudSize = viewHeightDp / RoundCloudsViewConstants.RELATIVELY_LARGE_CLOUD_SIZE
            val smallCloudSize = viewWidthDp / RoundCloudsViewConstants.RELATIVELY_SMALL_CLOUD_SIZE

            return CloudModelSizeHolder(
                viewCenterXCoordinateDp = centerX,
                viewCenterYCoordinateDp = centerY,
                largeCloudSizeDp = largeCloudSize,
                smallCloudSizeDp = smallCloudSize,
            )
        }
    }

    val largeCloudRadiusDp: Int
    get() = largeCloudSizeDp / 2

    val smallCloudRadiusDp: Int
    get() = smallCloudSizeDp / 2
}
