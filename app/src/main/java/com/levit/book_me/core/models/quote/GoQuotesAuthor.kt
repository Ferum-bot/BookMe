package com.levit.book_me.core.models.quote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoQuotesAuthor(
    @Json(name = "name")
    val fullName: String,

    @Json(name = "count")
    val numberOfQuotes: Int,
): Parcelable