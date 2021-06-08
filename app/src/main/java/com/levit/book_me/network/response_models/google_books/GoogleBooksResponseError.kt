package com.levit.book_me.network.response_models.google_books

import android.os.Parcelable
import com.levit.book_me.network.models.google_books.GoogleBooksError
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoogleBooksResponseError(
    @Json(name = "code")
    val errorStatusCode: Int,

    @Json(name = "message")
    val errorMessage: String?,

    @Json(name = "errors")
    val listOfErrors: List<GoogleBooksError>?,

    @Json(name = "status")
    val errorStatus: String?,
): Parcelable