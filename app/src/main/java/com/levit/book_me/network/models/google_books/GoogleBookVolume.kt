package com.levit.book_me.network.models.google_books

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoogleBookVolume(
    @SerializedName("kind")
    val kind: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("volumeInfo")
    val volumeResult: List<GoogleBook>
): Parcelable