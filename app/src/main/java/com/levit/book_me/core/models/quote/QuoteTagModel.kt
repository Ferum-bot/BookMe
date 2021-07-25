package com.levit.book_me.core.models.quote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class QuoteTagModel(

    @Json(name = "id")
    val id: Long = 0,

    @Json(name = "name")
    val name: String,

    @Json(name = "numberOfQuotes")
    val numberOfQuotes: Int,
): Parcelable