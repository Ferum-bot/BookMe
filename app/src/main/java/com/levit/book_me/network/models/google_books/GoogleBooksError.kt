package com.levit.book_me.network.models.google_books

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoogleBooksError(
    @SerializedName("message")
    val errorMessage: String?,

    @SerializedName("domain")
    val errorDomain: String?,

    @SerializedName("reason")
    val errorReason: String?,

    @SerializedName("location")
    val errorLocation: String?,

    @SerializedName("locationType")
    val errorLocationType: String?,
): Parcelable