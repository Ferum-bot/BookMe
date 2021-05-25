package com.levit.book_me.roundcloudsview.core.models

import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize

interface RoundCloud {

    /**
     * This text will be displayed in cloud.
     */
    val text: String

    /**
     * Cloud size.
     */
    val size: RoundCloudSize

}