package com.levit.book_me.roundcloudsview.entity.impl

import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.CloudTextModel
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.entity.CloudTextCoordinateCalculator

internal class TestCloudTextCoordinateCalculator: CloudTextCoordinateCalculator {

    override fun calculateCloudTextCoordinates(
        cloudModel: RoundCloudModel,
        sizeHolder: CloudModelSizeHolder
    ): CloudTextModel {
        return CloudTextModel("Hello world!")
    }
}