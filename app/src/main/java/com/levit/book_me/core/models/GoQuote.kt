package com.levit.book_me.core.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoQuote(
    @Json(name = "text")
    val text: String,

    @Json(name = "author")
    val authorFullName: String,

    @Json(name = "tag")
    val tag: String,
): Parcelable