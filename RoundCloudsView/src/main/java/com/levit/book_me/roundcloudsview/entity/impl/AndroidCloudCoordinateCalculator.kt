package com.levit.book_me.roundcloudsview.entity.impl

import android.content.Context
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel
import com.levit.book_me.roundcloudsview.entity.CloudCoordinateCalculator

internal class AndroidCloudCoordinateCalculator(

    private val getContext: () -> Context,

    private val dpToPx: (Int) -> Int,
    private val pxToDp: (Int) -> Int,
): CloudCoordinateCalculator {

    override fun calculateCloudModels(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder): List<RoundCloudModel> {
        return listOf()
    }

}