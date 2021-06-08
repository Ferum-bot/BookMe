package com.levit.book_me.network.models.google_books

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoogleBooksError(
    @Json(name = "message")
    val errorMessage: String?,

    @Json(name = "domain")
    val errorDomain: String?,

    @Json(name = "reason")
    val errorReason: String?,

    @Json(name = "location")
    val errorLocation: String?,

    @Json(name = "locationType")
    val errorLocationType: String?,
): Parcelable