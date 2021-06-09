package com.levit.book_me.roundcloudsview.entity.cloud_calculator.impl

import android.graphics.PointF
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.extensions.findLastLeftModel
import com.levit.book_me.roundcloudsview.core.extensions.findLastRightModel
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

    private lateinit var availableSmallClouds: MutableList<RoundCloud>

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
        val largeColumns = largeModels.getLargeColumns().reversed()
        availableSmallClouds = clouds.filter { cloud ->
            cloud.size == RoundCloudSize.SMALL
        }.toMutableList()
        var resultSmallClouds = mutableListOf<RoundCloudModel>()

        largeColumns.forEach { column ->
            val leftClouds = calculateSmallLeftClouds(column, largeColumns)
            val rightClouds = calculateSmallRightClouds(column, largeColumns)
            resultSmallClouds.addAll(leftClouds)
            resultSmallClouds.addAll(rightClouds)
        }

        resultSmallClouds = resultSmallClouds.filter { smallCloud ->
            largeModels.notIntercepts(smallCloud)
        }.toMutableList()

        return resultSmallClouds
    }

    /**
     * This method add some xOffset to all clouds to make clouds
     * be in the horizontal center of RoundCloudsView.
     */
    private fun moveModelsToCenter(
        smallModels: List<RoundCloudModel>, largeModels: List<RoundCloudModel>
    ): List<RoundCloudModel> {
        val leftModel = getLeftModel(smallModels, largeModels)
            ?: return largeModels + smallModels
        val rightModel = getRightModel(smallModels, largeModels)
            ?: return largeModels + smallModels
        val additionalXOffset = (rightModel.xOffsetPx - leftModel.xOffsetPx) / 3
        val resultList = mutableListOf<RoundCloudModel>()

        smallModels.forEach { cloud ->
            val newCloud = cloud.getWithNewOffsets(
                newXOffset = cloud.xOffsetPx - additionalXOffset,
            )
            resultList.add(newCloud)
        }
        largeModels.forEach { cloud ->
            val newCloud = cloud.getWithNewOffsets(
                newXOffset = cloud.xOffsetPx - additionalXOffset,
            )
            resultList.add(newCloud)
        }

        return resultList
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

    /**
     * This method moves all Large clouds to vertical center of
     * RoundCloudsView.
     */
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

    private fun calculateSmallLeftClouds(largeColumn: List<RoundCloudModel>, allColumns: List<List<RoundCloudModel>>): List<RoundCloudModel> {
        val resultClouds = mutableListOf<RoundCloudModel>()

        largeColumn.forEachIndexed { index, largeCloud ->
            if (availableSmallClouds.isEmpty()) {
                return@forEachIndexed
            }
            if (index >= largeColumn.size - 1) {
                return@forEachIndexed
            }
            val nextCloud = largeColumn[index + 1]
            val (distanceBetweenLargeClouds: Int, distanceBetweenSmallCloud: Int) =
                calculateSmallAndLargeDistance()
            val smallCloudXOffset = nextCloud.xOffsetPx - distanceBetweenSmallCloud
            val smallCloudYOffset = nextCloud.yOffsetPx + calculateYOffsetForSmallClouds(largeCloud, nextCloud)
            val currentCloud = availableSmallClouds.first()

            val newModel = RoundCloudModel(
                cloud = currentCloud,
                state = RoundCloudState.NOT_CHECKED,
                textModels = currentCloud.getDefaultTextModel(
                    defXOffset = smallCloudXOffset,
                    defYOffset = smallCloudYOffset,
                ),
                radiusPx = sizeHolder.smallCloudRadiusPx,
                xOffsetPx = smallCloudXOffset,
                yOffsetPx = smallCloudYOffset,
            )
            if (allColumns.intercepts(newModel)) {
                return@forEachIndexed
            }

            resultClouds.add(newModel)
            availableSmallClouds.removeFirst()
        }

        return resultClouds
    }

    private fun calculateSmallRightClouds(largeColumns: List<RoundCloudModel>, allColumns: List<List<RoundCloudModel>>): List<RoundCloudModel> {
        val resultClouds = mutableListOf<RoundCloudModel>()

        largeColumns.forEachIndexed { index, largeCloud ->
            if (availableSmallClouds.isEmpty()) {
                return@forEachIndexed
            }
            if (index >= largeColumns.size - 1) {
                return@forEachIndexed
            }
            val nextCloud = largeColumns[index + 1]
            val (distanceBetweenLargeClouds: Int, distanceBetweenSmallCloud: Int) =
                calculateSmallAndLargeDistance()
            val smallCloudXOffset = largeCloud.xOffsetPx + distanceBetweenSmallCloud
            val smallCloudYOffset = largeCloud.yOffsetPx - calculateYOffsetForSmallClouds(largeCloud, nextCloud)
            val currentCloud = availableSmallClouds.first()

            val newModel = RoundCloudModel(
                cloud = currentCloud,
                state = RoundCloudState.NOT_CHECKED,
                textModels = currentCloud.getDefaultTextModel(
                    defXOffset = smallCloudXOffset,
                    defYOffset = smallCloudYOffset,
                ),
                radiusPx = sizeHolder.smallCloudRadiusPx,
                xOffsetPx = smallCloudXOffset,
                yOffsetPx = smallCloudYOffset,
            )
            if (allColumns.intercepts(newModel)) {
                return@forEachIndexed
            }

            resultClouds.add(newModel)
            availableSmallClouds.removeFirst()
        }

        return resultClouds
    }

    private fun calculateSmallAndLargeDistance(): Pair<Int, Int> {
        val distanceBetweenLargeClouds = sizeHolder.largeCloudRadiusPx * 2 + sizeHolder.cloudMarginPx
        val distanceBetweenSmallCloud = sizeHolder.largeCloudRadiusPx + sizeHolder.smallCloudRadiusPx + sizeHolder.cloudMarginPx

        return distanceBetweenLargeClouds to distanceBetweenSmallCloud
    }

    private fun calculateYOffsetForSmallClouds(cloud: RoundCloudModel, nextCloud: RoundCloudModel): Int {
        val currentYOffset = cloud.yOffsetPx
        val nextYOffset = nextCloud.yOffsetPx
        return if (nextYOffset - currentYOffset >= 0) {
            sizeHolder.cloudMarginPx * 4
        } else {
            -sizeHolder.cloudMarginPx * 4
        }
    }

    private fun getLeftModel(smallClouds: List<RoundCloudModel>, largeModels: List<RoundCloudModel>): RoundCloudModel? {
        val smallLeftModel = smallClouds.findLastLeftModel()
        val largeLeftModel = largeModels.findLastLeftModel()
        val resultLeftModel: RoundCloudModel = smallLeftModel ?: largeLeftModel ?: return null

        if (largeLeftModel == null) {
            return resultLeftModel
        }
        return if (resultLeftModel.xOffsetPx >= largeLeftModel.xOffsetPx) {
            largeLeftModel
        } else {
            resultLeftModel
        }
    }

    private fun getRightModel(smallClouds: List<RoundCloudModel>, largeModels: List<RoundCloudModel>): RoundCloudModel? {
        val smallRightModel = smallClouds.findLastRightModel()
        val largeRightModel = largeModels.findLastRightModel()
        val resultLeftModel: RoundCloudModel = smallRightModel ?: largeRightModel ?: return null

        if (largeRightModel == null) {
            return resultLeftModel
        }
        return if (resultLeftModel.xOffsetPx <= largeRightModel.xOffsetPx) {
            largeRightModel
        } else {
            resultLeftModel
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

private fun List<RoundCloudModel>.getLargeColumns(): List<List<RoundCloudModel>> {
    val currentList = mutableListOf<RoundCloudModel>()
    val resultList = mutableListOf<List<RoundCloudModel>>()

    forEachIndexed { index, largeCloud ->
        if (index == size - 1) {
            currentList.add(largeCloud)
            return@forEachIndexed
        }
        if (index == 0) {
            currentList.add(largeCloud)
            return@forEachIndexed
        }

        val prevYOffset = get(index - 1).yOffsetPx
        val currentYOffset = largeCloud.yOffsetPx
        val nextYOffset = get(index + 1).yOffsetPx

        val firstValue = (nextYOffset - currentYOffset).toLong()
        val secondValue = (currentYOffset - prevYOffset).toLong()

        if (firstValue * secondValue >= 0) {
            currentList.add(largeCloud)
        } else {
            currentList.add(largeCloud)
            resultList.add(currentList.toList())
            currentList.clear()
            currentList.add(largeCloud)
        }
    }
    if (currentList.isNotEmpty()) {
        resultList.add(currentList.toList())
    }

    return resultList
}

private fun List<RoundCloudModel>.notIntercepts(cloud: RoundCloudModel): Boolean {
    val cloudCenter = PointF(cloud.xOffsetPx.toFloat(), cloud.yOffsetPx.toFloat())
    val cloudRadius = cloud.radiusPx

    forEach { currentCloud ->
        val currentCenter = PointF(currentCloud.xOffsetPx.toFloat(), currentCloud.yOffsetPx.toFloat())
        val currentRadius = currentCloud.radiusPx

        if (MathHelper.circlesIntercept(cloudCenter, cloudRadius, currentCenter, currentRadius)) {
            return false
        }
    }

    return true
}

private fun List<RoundCloudModel>.intercepts(cloud: RoundCloudModel): Boolean
    = !notIntercepts(cloud)

@JvmName("interceptsRoundCloudModel")
private fun List<List<RoundCloudModel>>.intercepts(cloud: RoundCloudModel): Boolean {
    forEach { column ->
        if (column.intercepts(cloud)) {
            return true
        }
    }
    return false
}