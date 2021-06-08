package com.levit.book_me.roundcloudsview.entity.cloud_calculator.impl

import android.content.Context
import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.entity.cloud_calculator.CloudCoordinateCalculator

internal class EmptyCloudCoordinateCalculator(

    private val getContext: () -> Context,

    private val dpToPx: (Int) -> Int,
    private val pxToDp: (Int) -> Int,
): CloudCoordinateCalculator {

    override fun calculateCloudModels(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder): List<RoundCloudModel> {
        return listOf()
    }

}