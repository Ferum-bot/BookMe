package com.levit.book_me.network.response_models.go_quotes

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuotesResponse<T: Parcelable> constructor(

    @Json(name = "data")
    val data: T?,

    @Json(name = "error")
    val error: QuotesErrorResponse?,

    @Json(name = "numberOfQuotes")
    val availableQuotes: Int,
    
    @Json(name = "numberOfTags")
    val availableTags: Int,

    @Json(name = "numberOfAuthors")
    val availableAuthors: Int,
): Parcelable
