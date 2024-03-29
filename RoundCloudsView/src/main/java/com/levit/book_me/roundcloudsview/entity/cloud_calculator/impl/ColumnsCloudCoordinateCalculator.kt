package com.levit.book_me.roundcloudsview.entity.cloud_calculator.impl

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.core.utills.RoundCloudsViewConstants
import com.levit.book_me.roundcloudsview.entity.cloud_calculator.CloudCoordinateCalculator

internal class ColumnsCloudCoordinateCalculator: CloudCoordinateCalculator {

    companion object {

        private const val LARGE_CLOUD_COUNT = RoundCloudsViewConstants.RELATIVELY_LARGE_CLOUD_SIZE
        private const val SMALL_CLOUD_COUNT = RoundCloudsViewConstants.RELATIVELY_SMALL_CLOUD_SIZE
    }

    private var sizeHolder: CloudModelSizeHolder = CloudModelSizeHolder()
    private var clouds = emptyList<RoundCloud>()

    override fun calculateCloudModels(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder): List<RoundCloudModel> {
        this.sizeHolder = sizeHolder
        this.clouds = clouds

        val resultList = mutableListOf<RoundCloudModel>()
        val listOfColumns = getListOfColumns()

        var leftXOffset = 0
        var rightXOffset = 0
        var currentIteration = Iterations.RIGHT_ITERATION

        listOfColumns.forEachIndexed { index, column ->
            val sizeType = column[0].size
            val cloudRadius = when(sizeType) {
                RoundCloudSize.LARGE -> sizeHolder.largeCloudRadiusPx
                RoundCloudSize.SMALL -> sizeHolder.smallCloudRadiusPx
            }
            val cloudMargin = sizeHolder.cloudMarginPx

            when(currentIteration) {
                Iterations.RIGHT_ITERATION -> {
                    if (index != 0) {
                        rightXOffset += cloudRadius
                    } else {
                        leftXOffset -= cloudRadius + cloudMargin
                    }
                    resultList += getColumnFrom(rightXOffset, sizeType, column)
                    rightXOffset += cloudRadius + cloudMargin
                    currentIteration = Iterations.LEFT_ITERATION
                }
                Iterations.LEFT_ITERATION -> {
                    leftXOffset -= cloudRadius
                    resultList += getColumnFrom(leftXOffset, sizeType, column)
                    leftXOffset -= cloudRadius + cloudMargin
                    currentIteration = Iterations.RIGHT_ITERATION
                }
            }
        }

        return resultList
    }

    private fun getListOfColumns(): List<List<RoundCloud>> {
        val largeClouds = clouds.filter { cloud ->
            cloud.size == RoundCloudSize.LARGE
        }
        val smallClouds = clouds.filter { cloud ->
            cloud.size == RoundCloudSize.SMALL
        }

        val resultList = mutableListOf<List<RoundCloud>>()
        var numberOfLargeColumns = largeClouds.size / RoundCloudsViewConstants.RELATIVELY_LARGE_CLOUD_SIZE
        if (largeClouds.size % RoundCloudsViewConstants.RELATIVELY_LARGE_CLOUD_SIZE != 0) {
            numberOfLargeColumns++
        }
        var numberOfSmallColumns = smallClouds.size / RoundCloudsViewConstants.RELATIVELY_SMALL_CLOUD_SIZE
        if (smallClouds.size % RoundCloudsViewConstants.RELATIVELY_SMALL_CLOUD_SIZE != 0) {
            numberOfSmallColumns++
        }
        val listCount = numberOfLargeColumns + numberOfSmallColumns

        var currentStartLargeIndex = 0
        var currentStartSmallIndex = 0

        for (i in 0 until listCount) {
            if (i % 2 == 0) {
                val currentEndLargeIndex = calculateLargeEndIndex(currentStartLargeIndex, largeClouds)
                currentEndLargeIndex ?: continue
                val largeSublist = largeClouds.subList(currentStartLargeIndex, currentEndLargeIndex)
                resultList.add(largeSublist)
                currentStartLargeIndex += LARGE_CLOUD_COUNT
                continue
            }
            val currentEndSmallIndex = calculateSmallEndIndex(currentStartSmallIndex, smallClouds)
            currentEndSmallIndex ?: continue
            val smallSublist = smallClouds.subList(currentStartSmallIndex, currentEndSmallIndex)
            resultList.add(smallSublist)
            currentStartSmallIndex += SMALL_CLOUD_COUNT
        }

        return resultList
    }

    private fun calculateSmallEndIndex(startIndex: Int, clouds: List<RoundCloud>): Int? {
        val size = clouds.size
        if (startIndex >= size) {
            return null
        }
        val endIndex = startIndex + SMALL_CLOUD_COUNT
        if (endIndex >= size) {
            return null
        }
        return endIndex
    }

    private fun calculateLargeEndIndex(startIndex: Int, clouds: List<RoundCloud>): Int? {
        val size = clouds.size
        if (startIndex >= size) {
            return null
        }
        val endIndex = startIndex + LARGE_CLOUD_COUNT
        if (endIndex >= size) {
            return size - 1
        }
        return endIndex
    }

    private fun getColumnFrom(viewXCoordinateOffset: Int, cloudTypeSize: RoundCloudSize, clouds: List<RoundCloud>): List<RoundCloudModel> {
        val cloudMargin = sizeHolder.cloudMarginPx
        val cloudSize = when(cloudTypeSize) {
            RoundCloudSize.LARGE -> sizeHolder.largeCloudSizePx
            RoundCloudSize.SMALL -> sizeHolder.smallCloudSizePx
        }
        val cloudRadius = when(cloudTypeSize) {
            RoundCloudSize.LARGE -> sizeHolder.largeCloudRadiusPx
            RoundCloudSize.SMALL -> sizeHolder.smallCloudRadiusPx
        }
        val initialYOffset = -sizeHolder.viewCenterYCoordinatePx
        var currentYOffset = initialYOffset + cloudRadius + cloudMargin
        val resultList = mutableListOf<RoundCloudModel>()

        clouds.forEach { cloud ->
            val currentModel = RoundCloudModel(
                cloud = cloud,
                state = RoundCloudState.NOT_CHECKED,
                radiusPx = cloudRadius,
                xOffsetPx = viewXCoordinateOffset,
                yOffsetPx = currentYOffset
            )
            resultList.add(currentModel)

            currentYOffset += cloudSize + cloudMargin
        }
        return resultList
    }

}

private enum class Iterations {
    LEFT_ITERATION, RIGHT_ITERATION;
}