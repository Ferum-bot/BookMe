package com.levit.book_me.core.models

import android.os.Parcelable
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudSize
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(

    @Json(name = "")
    val id: Long = 0,

    @Json(name = "")
    val string: String = "",

    @Json(name = "")
    val isBig: Boolean = true
): Parcelable, RoundCloud {

    override val size: RoundCloudSize
    get() {
        return if (isBig)
            RoundCloudSize.LARGE
        else
            RoundCloudSize.SMALL
    }

    override val text: String
    get() = string

}
