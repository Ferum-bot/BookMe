package com.levit.book_me.roundcloudsview.core.extensions

import com.levit.book_me.roundcloudsview.core.models.CloudTextModel
import com.levit.book_me.roundcloudsview.core.models.RoundCloud

internal fun RoundCloud.getDefaultTextModel(
    defXOffset: Int = 0,
    defYOffset: Int = 0,
): List<CloudTextModel> {
    return listOf(CloudTextModel(
        text = text,
        textXOffsetPx = defXOffset,
        textYOffsetPx = defYOffset,
    ))
}