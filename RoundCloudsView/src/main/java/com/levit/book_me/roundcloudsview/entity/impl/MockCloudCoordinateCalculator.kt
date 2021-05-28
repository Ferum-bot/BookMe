package com.levit.book_me.roundcloudsview.entity.impl

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.entity.CloudCoordinateCalculator

internal class MockCloudCoordinateCalculator: CloudCoordinateCalculator {

    private val testCloud = object: RoundCloud {

        override val size: RoundCloudSize
            get() = RoundCloudSize.LARGE

        override val text: String
            get() = "Test Cloud"
    }

    override fun calculateCloudModels(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder): List<RoundCloudModel> {
        return listOf(
            RoundCloudModel(
                cloud = testCloud,
                radiusPx = 50,
                xOffsetPx = 0,
                yOffsetPx = 0,
            )
        )
    }

}