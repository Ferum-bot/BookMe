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
): Parcelable {

    fun getBiggestAvailableLink(): String? {
        if (extraLargeImageLink != null) {
            return extraLargeImageLink
        }
        if (largeImageLink != null) {
            return largeImageLink
        }
        if (mediumImageLink != null) {
            return mediumImageLink
        }
        if (defaultThumbnailImageLink != null) {
            return defaultThumbnailImageLink
        }
        if (smallImageLink != null) {
            return smallImageLink
        }
        if (smallThumbnailImageLink != null) {
            return smallThumbnailImageLink
        }
        return null
    }

    fun getSmallestAvailableLink(): String? {
        if (smallThumbnailImageLink != null) {
            return smallThumbnailImageLink
        }
        if (smallImageLink != null) {
            return smallImageLink
        }
        if (defaultThumbnailImageLink != null) {
            return defaultThumbnailImageLink
        }
        if (mediumImageLink != null) {
            return mediumImageLink
        }
        if (largeImageLink != null) {
            return largeImageLink
        }
        if (extraLargeImageLink != null) {
            return extraLargeImageLink
        }
        return null
    }
}