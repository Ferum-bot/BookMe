package com.levit.book_me.roundcloudsview.entity.impl

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.entity.CloudCoordinateCalculator

internal class MockCloudCoordinateCalculator: CloudCoordinateCalculator {

    private val testCloud1 = object: RoundCloud {

        override val size: RoundCloudSize
            get() = RoundCloudSize.LARGE

        override val text: String
            get() = "LargeCloud"
    }

    private val testCloud2 = object: RoundCloud {

        override val size: RoundCloudSize
            get() = RoundCloudSize.SMALL

        override val text: String
            get() = "Small Cloud"

    }

    override fun calculateCloudModels(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder): List<RoundCloudModel> {
        return listOf(
            RoundCloudModel(
                cloud = testCloud1,
                radiusPx = sizeHolder.largeCloudRadiusPx,
                xOffsetPx = 0,
                yOffsetPx = 0,
            ),
            RoundCloudModel(
                cloud = testCloud2,
                radiusPx = sizeHolder.smallCloudRadiusPx,
                xOffsetPx = sizeHolder.smallCloudRadiusPx * 3,
                yOffsetPx = sizeHolder.smallCloudRadiusPx * 3,
            )
        )
    }

}