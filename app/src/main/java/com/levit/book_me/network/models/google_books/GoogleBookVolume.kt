package com.levit.book_me.network.models.google_books

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class GoogleBookVolume(
    @Json(name = "kind")
    val kind: String,

    @Json(name = "id")
    val id: String,

    @Json(name = "volumeInfo")
    val volumeResult: GoogleBook
): Parcelable