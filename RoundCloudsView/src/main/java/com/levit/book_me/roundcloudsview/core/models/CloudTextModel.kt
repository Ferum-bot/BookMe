package com.levit.book_me.roundcloudsview.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class CloudTextModel(

    val text: String,

    /**
     * The text left x coordinate offset relatively RoundCloudsView
     * @center x coordinate.
     */
    val textXOffsetPx: Int = 0,

    /**
     * The text bottom y coordinate offset relatively RoundCloudsView
     * @center y coordinate.
     */
    val textYOffsetPx: Int = 0,
): Parcelable {

    fun getXCoordinatePx(viewCenterPx: Int): Int {
        return textXOffsetPx + viewCenterPx
    }

    fun getYCoordinatePx(viewCenterPx: Int): Int {
        return textYOffsetPx + viewCenterPx
    }
}
