package com.levit.book_me.core.models.profile

import android.os.Parcelable
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.network.models.google_books.GoogleBook
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


@Parcelize
data class ProfileModel(

    @Json(name = "")
    val name: String = "",

    @Json(name = "")
    val surname: String = "",

    @Json(name = "")
    val wordsAboutPerson: String = "",

    @Json(name = "")
    val profilePhotoUrl: String = "",

    @Json(name = "")
    val favouriteGenres: List<Genre> = emptyList(),

    @Json(name = "")
    val favouriteAuthors: List<Author> = emptyList(),

    @Json(name = "")
    val favouriteBooks: List<GoogleBook> = emptyList(),

    @Json(name = "")
    val wantToReadBooks: List<GoogleBook> = emptyList(),

    @Json(name = "")
    val quote: QuoteModel = QuoteModel(),
): Parcelable {

    fun copyWithNew(
        name: String? = null, surname: String? = null, wordsAboutPerson: String? = null, profilePhotoUrl: String? = null,
        favouriteGenres: List<Genre>? = null, favouriteAuthors: List<Author>? = null, favouriteBooks: List<GoogleBook>? = null,
        wantToReadBooks: List<GoogleBook>? = null, quote: QuoteModel? = null,
    ): ProfileModel {
        val newName = name ?: this.name
        val newSurname = surname ?: this.surname
        val newWordsAboutPerson = wordsAboutPerson ?: this.wordsAboutPerson
        val newProfilePhotoUrl = profilePhotoUrl ?: this.profilePhotoUrl
        val newFavoriteGenres = favouriteGenres ?: this.favouriteGenres
        val newFavoriteAuthors = favouriteAuthors ?: this.favouriteAuthors
        val newFavoriteBooks = favouriteBooks ?: this.favouriteBooks
        val newWantToReadBooks = wantToReadBooks ?: this.wantToReadBooks
        val newQuote = quote ?: this.quote

        return ProfileModel(
            name = newName,
            surname = newSurname,
            wordsAboutPerson = newWordsAboutPerson,
            profilePhotoUrl = newProfilePhotoUrl,
            favouriteGenres = newFavoriteGenres,
            favouriteAuthors = newFavoriteAuthors,
            favouriteBooks = newFavoriteBooks,
            wantToReadBooks = newWantToReadBooks,
            quote = newQuote,
        )
    }
}
