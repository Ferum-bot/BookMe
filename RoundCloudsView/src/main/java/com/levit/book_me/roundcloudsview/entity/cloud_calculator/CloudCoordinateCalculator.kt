package com.levit.book_me.roundcloudsview.entity.cloud_calculator

import com.levit.book_me.roundcloudsview.core.models.CloudModelSizeHolder
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.roundcloudsview.core.models.RoundCloudModel

internal interface CloudCoordinateCalculator {

    /**
     * This method requests when view needs to calculate all clouds coordinates
     * to show it on screen. Use this interface if you want to add custom view
     * arrangement.
     */
    fun calculateCloudModels(clouds: List<RoundCloud>, sizeHolder: CloudModelSizeHolder): List<RoundCloudModel>

}