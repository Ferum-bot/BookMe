package com.levit.book_me.network.response_models.go_quotes

import android.os.Parcelable
import com.levit.book_me.core.models.quote.GoQuotesTag
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoQuotesTagsResponse(
    @Json(name = "status")
    val statusCode: Int,

    @Json(name = "message")
    val statusMessage: String,

    @Json(name = "count")
    val resultCount: Int?,

    @Json(name = "tags")
    val tags: List<GoQuotesTag>?
): Parcelable