package com.levit.book_me.core.models.quote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoQuotesTag(
    @Json(name = "name")
    val name: String,

    @Json(name = "count")
    val numberOfQuotes: Int,
): Parcelable