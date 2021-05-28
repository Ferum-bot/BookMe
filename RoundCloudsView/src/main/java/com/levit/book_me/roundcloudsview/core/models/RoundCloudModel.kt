package com.levit.book_me.roundcloudsview.core.models

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState

internal class RoundCloudModel(

    val cloud: RoundCloud,

    var state: RoundCloudState = RoundCloudState.NOT_CHECKED,

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

}