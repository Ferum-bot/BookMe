package com.levit.book_me.network.response_models.quotes

import com.squareup.moshi.Json

interface QuotesResponse<T> {

    @Json(name = "data")
    val data: T?

    @Json(name = "error")
    val error: QuotesErrorResponse?

    @Json(name = "numberOfQuotes")
    val availableQuotes: Int

    @Json(name = "numberOfTags")
    val availableTags: Int

    @Json(name = "numberOfAuthors")
    val availableAuthors: Int

}