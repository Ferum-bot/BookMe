package com.levit.book_me.core.models.quote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class QuoteAuthorModel(

    @Json(name = "id")
    val id: Long = 0,

    @Json(name = "fullName")
    val fullName: String,

    @Json(name = "numberOfQuotes")
    val numberOfQuotes: Int,
): Parcelable