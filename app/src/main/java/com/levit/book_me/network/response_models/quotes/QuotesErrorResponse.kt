package com.levit.book_me.network.response_models.quotes

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class QuotesErrorResponse(

    @Json(name = "statusCode")
    val statusCode: Int,

    @Json(name = "errorMessage")
    val message: String,
): Parcelable
