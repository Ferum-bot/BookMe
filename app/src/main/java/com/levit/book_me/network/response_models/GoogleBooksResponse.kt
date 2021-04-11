package com.levit.book_me.network.response_models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.levit.book_me.network.models.google_books.GoogleBookVolume
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoogleBooksResponse(
    @SerializedName("kind")
    val responseKind: String?,

    @SerializedName("totalItems")
    val totalItems: Int?,

    @SerializedName("items")
    val responseResult: GoogleBookVolume?,

    @SerializedName("error")
    val errorResponse: GoogleBooksResponseError?
): Parcelable