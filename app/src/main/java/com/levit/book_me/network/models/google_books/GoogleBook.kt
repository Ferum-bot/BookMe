package com.levit.book_me.network.models.google_books

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoogleBook(
    @SerializedName("title")
    val title: String,

    @SerializedName("authors")
    val listOfAuthors: List<String>,

    @SerializedName("printType")
    val printType: String,

    @SerializedName("categories")
    val bookCategories: List<String>,

    @SerializedName("imageLinks")
    val imageLinks: ImageLinks?,

    @SerializedName("language")
    val bookLanguage: String,

    @SerializedName("previewLink")
    val previewLink: String,
): Parcelable