package com.levit.book_me.network.models.google_books

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageLinks(
    @SerializedName("smallThumbnail")
    val smallThumbnailImageLink: String?,

    @SerializedName("thumbnail")
    val defaultThumbnailImageLink: String?,

    @SerializedName("small")
    val smallImageLink: String?,

    @SerializedName("medium")
    val mediumImageLink: String?,

    @SerializedName("large")
    val largeImageLink: String?,

    @SerializedName("extraLarge")
    val extraLargeImageLink: String?
): Parcelable