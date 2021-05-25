package com.levit.book_me.roundcloudsview.core.extensions

import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel

internal fun List<RoundCloud>.toRoundCloudModels(): List<RoundCloudModel> {
    return mutableListOf<RoundCloudModel>().apply {
        this@toRoundCloudModels.forEach { roundCloud ->
            val roundCloudModel = RoundCloudModel(roundCloud)
            add(roundCloudModel)
        }
    }
}