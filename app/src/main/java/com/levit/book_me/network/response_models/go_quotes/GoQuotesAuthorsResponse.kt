package com.levit.book_me.network.response_models.go_quotes

import android.os.Parcelable
import com.levit.book_me.core.models.GoQuotesAuthor
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoQuotesAuthorsResponse(
    @Json(name = "status")
    val statusCode: Int,

    @Json(name = "message")
    val statusMessage: String,

    @Json(name = "count")
    val resultCount: Int?,

    @Json(name = "authors")
    val authors: List<GoQuotesAuthor>?
): Parcelable