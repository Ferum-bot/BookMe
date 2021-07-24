package com.levit.book_me.core.models.quote

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuotesMainScreenModel(

    @Json(name = "tagsCount")
    val numberOfTags: Int,

    @Json(name = "authorsCount")
    val numberOfAuthors: Int,

    @Json(name = "randomQuotes")
    val randomQuotes: List<QuoteModel>
): Parcelable