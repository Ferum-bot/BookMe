package com.levit.book_me.network.models.google_books

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GoogleBook(
    @Json(name = "title")
    val title: String,

    @Json(name = "authors")
    val listOfAuthors: List<String>?,

    @Json(name = "printType")
    val printType: String,

    @Json(name = "categories")
    val bookCategories: List<String>?,

    @Json(name = "imageLinks")
    val imageLinks: ImageLinks?,

    @Json(name = "language")
    val bookLanguage: String,

    @Json(name = "previewLink")
    val previewLink: String,
): Parcelable