package com.levit.book_me.roundcloudsview.core.models

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState

internal class RoundCloudModel(
    val cloud: RoundCloud,

    var state: RoundCloudState = RoundCloudState.NOT_CHECKED,

    var dpRadius: Int = 0,

    var centerX: Int = 0,

    var centerY: Int = 0
) {

}