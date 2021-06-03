package com.levit.book_me.roundcloudsview.entity.cloud_calculator.impl

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.entity.cloud_calculator.CloudCoordinateCalculator

/**
 * This coordinate calculator requests exactly five large clouds
 * and at least 16 small clouds for correct work.
 *
 * @Important
 * If conditions are not met, returns empty list.
 */
internal class BeautifulCloudCoordinateCalculator: CloudCoordinateCalculator {

    companion object {

        private const val PERMISSIBLE_LARGE_CLOUDS_COUNT = 5
        private const val AT_LEAST_SMALL_CLOUDS_COUNT = 16
    }

    private lateinit var clouds: List<RoundCloud>
    private lateinit var sizeHolder: CloudModelSizeHolder

    override fun calculateCloudModels(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder): List<RoundCloudModel> {
        if (conditionsAreMissing(clouds)) {
            return emptyList()
        }
        initProperties(clouds, sizeHolder)

        val largeCloudModels = calculateLargeModelsCoordinates()
        val smallCloudModels = calculateSmallModelsWith(largeCloudModels)

        return moveModelsToCenter(
            smallModels = smallCloudModels,
            largeModels = largeCloudModels,
        )
    }

    private fun conditionsAreMissing(clouds: List<RoundCloud>): Boolean {
        val numberOfLargeClouds = clouds.count { cloud ->
            cloud.size == RoundCloudSize.LARGE
        }
        val numberOfSmallClouds = clouds.size - numberOfLargeClouds

        if (numberOfLargeClouds != PERMISSIBLE_LARGE_CLOUDS_COUNT) {
            return true
        }
        if (numberOfSmallClouds < AT_LEAST_SMALL_CLOUDS_COUNT) {
            return true
        }
        return false
    }

    private fun initProperties(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder) {
        this.clouds = clouds
        this.sizeHolder = sizeHolder
    }

    private fun calculateLargeModelsCoordinates(): List<RoundCloudModel> {

        return emptyList()
    }

    private fun calculateSmallModelsWith(largeModels: List<RoundCloudModel>): List<RoundCloudModel> {

        return emptyList()
    }

    private fun moveModelsToCenter(
        smallModels: List<RoundCloudModel>, largeModels: List<RoundCloudModel>
    ): List<RoundCloudModel> {

        return largeModels + smallModels
    }
}