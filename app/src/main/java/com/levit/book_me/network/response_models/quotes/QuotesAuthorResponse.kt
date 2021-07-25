package com.levit.book_me.network.response_models.quotes

import android.os.Parcelable
import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuotesAuthorResponse(

    @Json(name = "data")
    override val data: List<QuoteAuthorModel>?,

    @Json(name = "error")
    override val error: QuotesErrorResponse?,

    @Json(name = "numberOfQuotes")
    override val availableQuotes: Int,

    @Json(name = "numberOfTags")
    override val availableTags: Int,

    @Json(name = "numberOfAuthors")
    override val availableAuthors: Int,

): Parcelable, QuotesResponse<List<QuoteAuthorModel>>
