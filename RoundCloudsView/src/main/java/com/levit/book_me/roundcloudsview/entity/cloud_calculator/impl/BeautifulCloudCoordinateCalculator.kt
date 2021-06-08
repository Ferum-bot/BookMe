package com.levit.book_me.roundcloudsview.entity.cloud_calculator.impl

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.extensions.getDefaultTextModel
import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.core.utills.MathHelper
import com.levit.book_me.roundcloudsview.entity.cloud_calculator.CloudCoordinateCalculator
import kotlin.math.sin

internal class BeautifulCloudCoordinateCalculator: CloudCoordinateCalculator {

    private lateinit var clouds: List<RoundCloud>
    private lateinit var sizeHolder: CloudModelSizeHolder

    private var currentLargeCloudDestination = NewLargeCloudDestination.DOWN

    override fun calculateCloudModels(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder): List<RoundCloudModel> {
        initProperties(clouds, sizeHolder)

        val largeCloudModels = calculateLargeModelsCoordinates()
        val smallCloudModels = calculateSmallModelsWith(largeCloudModels)

        return moveModelsToCenter(
            smallModels = smallCloudModels,
            largeModels = largeCloudModels,
        )
    }

    private fun initProperties(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder) {
        this.clouds = clouds
        this.sizeHolder = sizeHolder
    }

    private fun calculateLargeModelsCoordinates(): List<RoundCloudModel> {
        val largeClouds = clouds.filter { cloud ->
            cloud.size == RoundCloudSize.LARGE
        }
        val resultList = mutableListOf<RoundCloudModel>()
        var prevCloud: RoundCloudModel? = null

        largeClouds.forEach { cloud ->
            val currentCloud = calculateNewLargeCloudFrom(prevCloud, cloud)
            resultList.add(currentCloud)
            prevCloud = currentCloud
        }

        return moveAllLargeCloudsToCenter(resultList)
    }

    private fun calculateSmallModelsWith(largeModels: List<RoundCloudModel>): List<RoundCloudModel> {

        return emptyList()
    }

    private fun moveModelsToCenter(
        smallModels: List<RoundCloudModel>, largeModels: List<RoundCloudModel>
    ): List<RoundCloudModel> {

        return largeModels + smallModels
    }

    private fun calculateNewLargeCloudFrom(previousCloud: RoundCloudModel?, cloud: RoundCloud): RoundCloudModel {
        if (previousCloud == null) {
            return calculateFirstLargeCloud(cloud)
        }

        val destination = calculateNewLargeCloudDestination(previousCloud)
        return when (destination) {
            NewLargeCloudDestination.UP ->
                calculateNewLargeCloudUp(previousCloud, cloud)
            NewLargeCloudDestination.DOWN ->
                calculateNewLargeCloudDown(previousCloud, cloud)
        }
    }

    private fun calculateFirstLargeCloud(cloud: RoundCloud): RoundCloudModel {
        val startXOffset = -sizeHolder.viewCenterXCoordinatePx + sizeHolder.largeCloudRadiusPx
        val startYOffset = -sizeHolder.viewCenterYCoordinatePx + sizeHolder.largeCloudRadiusPx

        return RoundCloudModel(
            cloud = cloud,
            state = RoundCloudState.NOT_CHECKED,
            textModels = cloud.getDefaultTextModel(
                defXOffset = startXOffset,
                defYOffset = startYOffset,
            ),
            radiusPx = sizeHolder.largeCloudRadiusPx,
            xOffsetPx = startXOffset,
            yOffsetPx = startYOffset,
        )
    }

    private fun calculateNewLargeCloudDestination(
        previousCloud: RoundCloudModel
    ): NewLargeCloudDestination {
        val neededXOffset = calculateNeededLargeXOffset()
        val neededYOffset = when(currentLargeCloudDestination) {
            NewLargeCloudDestination.UP ->
                -neededXOffset
            NewLargeCloudDestination.DOWN ->
                neededXOffset
        }

        var neededCloudY = previousCloud.yOffsetPx + neededYOffset + sizeHolder.viewCenterYCoordinatePx
        if (!viewContainsCloudWith(neededCloudY, sizeHolder.largeCloudRadiusPx)) {
            neededCloudY *= -1
            currentLargeCloudDestination = currentLargeCloudDestination.opposite
        }

        return currentLargeCloudDestination
    }

    private fun calculateNewLargeCloudUp(previousCloud: RoundCloudModel, cloud: RoundCloud): RoundCloudModel {
        val neededXOffset = calculateNeededLargeXOffset()
        val neededYOffset = -neededXOffset
        val actualXOffset = neededXOffset + previousCloud.xOffsetPx
        val actualYOffset = neededYOffset + previousCloud.yOffsetPx

        return RoundCloudModel(
            cloud = cloud,
            state = RoundCloudState.NOT_CHECKED,
            textModels = cloud.getDefaultTextModel(
                defXOffset = actualXOffset,
                defYOffset = actualYOffset,
            ),
            radiusPx = sizeHolder.largeCloudRadiusPx,
            xOffsetPx = actualXOffset,
            yOffsetPx = actualYOffset,
        )
    }

    private fun calculateNewLargeCloudDown(previousCloud: RoundCloudModel, cloud: RoundCloud): RoundCloudModel {
        val neededXOffset = calculateNeededLargeXOffset()
        val actualXOffset = neededXOffset + previousCloud.xOffsetPx
        val actualYOffset = neededXOffset + previousCloud.yOffsetPx

        return RoundCloudModel(
            cloud = cloud,
            state = RoundCloudState.NOT_CHECKED,
            textModels = cloud.getDefaultTextModel(
                defXOffset = actualXOffset,
                defYOffset = actualYOffset,
            ),
            radiusPx = sizeHolder.largeCloudRadiusPx,
            xOffsetPx = actualXOffset,
            yOffsetPx = actualYOffset,
        )
    }

    private fun viewContainsCloudWith(cloudYCoordinate: Int, radiusPx: Int): Boolean {
        val viewHeight = sizeHolder.viewCenterYCoordinatePx * 2
        val downCloudYCoordinate = cloudYCoordinate + radiusPx
        val upCloudYCoordinate = cloudYCoordinate - radiusPx

        if (upCloudYCoordinate < 0) {
            return false
        }
        if (downCloudYCoordinate > viewHeight) {
            return false
        }
        return true
    }

    private fun calculateNeededLargeXOffset(): Int {
        val neededDistanceBetweenClouds = 2 * sizeHolder.largeCloudRadiusPx + sizeHolder.cloudMarginPx
        return (neededDistanceBetweenClouds * sin(MathHelper.ANGLE_45_RAD)).toInt()
    }

    private fun moveAllLargeCloudsToCenter(clouds: List<RoundCloudModel>): List<RoundCloudModel> {
        val topOffsetCoordinate = clouds.findTopCloud()?.yOffsetPx
            ?: return clouds
        val bottomOffsetCoordinate = clouds.findBottomCloud()?.yOffsetPx
            ?: return clouds
        val cloudsVerticalCenter = (topOffsetCoordinate + bottomOffsetCoordinate + 2 * sizeHolder.viewCenterYCoordinatePx) / 2
        val viewCenter = sizeHolder.viewCenterYCoordinatePx
        val additionalYOffset = viewCenter - cloudsVerticalCenter

        return clouds.map { oldCloud ->
            val newYOffset = oldCloud.yOffsetPx + additionalYOffset
            oldCloud.getWithNewOffsets(
                newYOffset = newYOffset,
            )
        }
    }
}

private enum class NewLargeCloudDestination {
    UP {

        override val opposite: NewLargeCloudDestination
            get() = DOWN
    },

    DOWN {

        override val opposite: NewLargeCloudDestination
            get() = UP
    };

    abstract val opposite: NewLargeCloudDestination
}

private fun List<RoundCloudModel>.findTopCloud(): RoundCloudModel? {
    var minOffset = Int.MAX_VALUE
    var resultCloud: RoundCloudModel? = null
    forEach { cloud ->
        if (cloud.yOffsetPx < minOffset) {
            minOffset = cloud.yOffsetPx
            resultCloud = cloud
        }
    }
    return resultCloud
}

private fun List<RoundCloudModel>.findBottomCloud(): RoundCloudModel? {
    var maxOffset = Int.MIN_VALUE
    var resultCloud: RoundCloudModel? = null
    forEach { cloud ->
        if (cloud.yOffsetPx > maxOffset) {
            maxOffset = cloud.yOffsetPx
            resultCloud = cloud
        }
    }
    return resultCloud
}