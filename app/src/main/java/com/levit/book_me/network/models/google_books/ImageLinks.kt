package com.levit.book_me.network.models.google_books

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageLinks(
    @Json(name = "smallThumbnail")
    val smallThumbnailImageLink: String?,

    @Json(name = "thumbnail")
    val defaultThumbnailImageLink: String?,

    @Json(name = "small")
    val smallImageLink: String?,

    @Json(name = "medium")
    val mediumImageLink: String?,

    @Json(name = "large")
    val largeImageLink: String?,

    @Json(name = "extraLarge")
    val extraLargeImageLink: String?
): Parcelable