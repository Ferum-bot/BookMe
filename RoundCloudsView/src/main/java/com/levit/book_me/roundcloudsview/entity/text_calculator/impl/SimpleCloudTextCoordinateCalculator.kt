package com.levit.book_me.roundcloudsview.entity.text_calculator.impl

import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.CloudTextModel
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.entity.text_calculator.CloudTextCoordinateCalculator

/**
 * This text coordinate calculator only move text to center of the cloud.
 * This calculator does not support too long, multi words and multi lines text.
 * Works only with center Paint.
 */
internal class SimpleCloudTextCoordinateCalculator: CloudTextCoordinateCalculator {

    companion object {

        private const val ADDITIONAL_HORIZONTAL_OFFSET = 4
    }

    override fun calculateCloudTextCoordinates(
        cloudModel: RoundCloudModel,
        sizeHolder: CloudModelSizeHolder
    ): List<CloudTextModel> {
        val textXOffset = cloudModel.xOffsetPx
        val textYOffset = cloudModel.yOffsetPx + ADDITIONAL_HORIZONTAL_OFFSET

        val resultModel = CloudTextModel(
            text = cloudModel.text,
            textXOffsetPx = textXOffset,
            textYOffsetPx = textYOffset,
        )
        return listOf(resultModel)
    }

}