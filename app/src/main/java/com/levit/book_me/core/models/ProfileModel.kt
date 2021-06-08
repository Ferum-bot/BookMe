package com.levit.book_me.core.models

import android.os.Parcelable
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.network.models.google_books.GoogleBook
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileModel(

    @Json(name = "")
    val name: String,

    @Json(name = "")
    val surname: String,

    @Json(name = "")
    val wordsAboutPerson: String,

    @Json(name = "")
    val profilePhotoUrl: String,

    @Json(name = "")
    val favouriteGenres: List<Genre>,

    @Json(name = "")
    val favouriteAuthors: List<Author>,

    @Json(name = "")
    val favouriteBooks: List<GoogleBook>,

    @Json(name = "")
    val wantToReadBooks: List<GoogleBook>,

    @Json(name = "")
    val quote: GoQuote,
): Parcelable
