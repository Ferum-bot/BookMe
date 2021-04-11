package com.levit.book_me.network.response_models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.levit.book_me.network.models.google_books.GoogleBooksError
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoogleBooksResponseError(
    @SerializedName("code")
    val errorStatusCode: Int?,

    @SerializedName("message")
    val errorMessage: String?,

    @SerializedName("errors")
    val listOfErrors: List<GoogleBooksError>?,

    @SerializedName("status")
    val errorStatus: String?
): Parcelable