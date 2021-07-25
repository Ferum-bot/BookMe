package com.levit.book_me.network.response_models.quotes

import android.os.Parcelable
import com.levit.book_me.core.models.quote.QuoteModel
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuotesQuoteResponse(

    @Json(name = "data")
    override val data: List<QuoteModel>?,

    @Json(name = "error")
    override val error: QuotesErrorResponse?,

    @Json(name = "numberOfQuotes")
    override val availableQuotes: Int,

    @Json(name = "numberOfTags")
    override val availableTags: Int,

    @Json(name = "numberOfAuthors")
    override val availableAuthors: Int,

): Parcelable, QuotesResponse<List<QuoteModel>>
