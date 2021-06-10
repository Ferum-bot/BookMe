package com.levit.book_me.roundcloudsview.core.models

import android.os.Parcelable
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import kotlinx.android.parcel.Parcelize

interface RoundCloud: Parcelable {

    /**
     * This text will be displayed in cloud.
     */
    val text: String

    /**
     * Cloud size.
     */
    val size: RoundCloudSize

}