package com.levit.book_me.roundcloudsview.core.extensions

import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import kotlin.math.max
import kotlin.math.min

internal fun List<RoundCloud>.toRoundCloudModels(): List<RoundCloudModel> {
    return mutableListOf<RoundCloudModel>().apply {
        this@toRoundCloudModels.forEach { roundCloud ->
            val roundCloudModel = RoundCloudModel(roundCloud)
            add(roundCloudModel)
        }
    }
}

internal fun List<RoundCloudModel>.findLastRightModel(): RoundCloudModel? {
    if (isEmpty()) {
        return null
    }
    var maxXOffset = Int.MIN_VALUE
    var rightModel: RoundCloudModel? = null
    forEach { model ->
        if (maxXOffset < (model.xOffsetPx + model.radiusPx)) {
            rightModel = model
        }
        maxXOffset = max(maxXOffset, model.xOffsetPx + model.radiusPx)
    }
    return rightModel
}

internal fun List<RoundCloudModel>.findLastLeftModel(): RoundCloudModel? {
    if (isEmpty()) {
        return null
    }
    var minXOffset = Int.MAX_VALUE
    var leftModel: RoundCloudModel? = null
    forEach { model ->
        if (minXOffset > (model.xOffsetPx - model.radiusPx)) {
            leftModel = model
        }
        minXOffset = min(minXOffset, model.xOffsetPx - model.radiusPx)
    }
    return leftModel
}

internal fun List<RoundCloudModel>.getRawRoundClouds(): List<RoundCloud> {
    val resultList = mutableListOf<RoundCloud>()
    forEach { cloudModel ->
        resultList.add(cloudModel.cloud)
    }
    return resultList
}

internal fun List<RoundCloud>.isEqual(other: List<RoundCloud>): Boolean {
    if (isEmpty()) {
        return false
    }
    forEach { currentModel ->
        if (!other.contains(currentModel)) {
            return false
        }
    }
    return true
}