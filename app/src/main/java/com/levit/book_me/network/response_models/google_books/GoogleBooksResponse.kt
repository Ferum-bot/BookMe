package com.levit.book_me.network.response_models.google_books

import android.os.Parcelable
import com.levit.book_me.network.models.google_books.GoogleBookVolume
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class GoogleBooksResponse(
    @Json(name = "kind")
    val responseKind: String?,

    @Json(name = "totalItems")
    val totalItems: Int?,

    @Json(name = "items")
    val responseResult: List<GoogleBookVolume>?,

    @Json(name = "error")
    val errorResponse: GoogleBooksResponseError?
): Parcelable