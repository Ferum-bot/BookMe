package com.levit.book_me.core.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(

    @Json(name = "")
    val id: Long,

    @Json(name = "")
    val string: String,

    @Json(name = "")
    val isBig: Boolean = true
): Parcelable
