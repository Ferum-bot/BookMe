package com.levit.book_me.roundcloudsview.entity

import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.CloudTextModel
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel

internal interface CloudTextCoordinateCalculator {

    /**
     * This method requests when we needed to calculate the right
     * text coordinates of the current cloud model to show it on the screen.
     */
    fun calculateCloudTextCoordinates(cloudModel: RoundCloudModel, sizeHolder: CloudModelSizeHolder): List<CloudTextModel>
}