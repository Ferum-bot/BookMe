package com.levit.book_me.repositories.profile

import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import com.levit.book_me.repositories.result_models.ProfileRepositoryResult
import kotlinx.coroutines.flow.SharedFlow

interface ProfileRepository
    : FirebaseStorageUploadUriRepository{

    val resultProfile: SharedFlow<ProfileRepositoryResult<ProfileModel>>

    suspend fun updateBaseInformation(name: String?, surname: String?, wordsAboutPerson: String?)

    suspend fun updateQuote(quote: GoQuote)

    suspend fun updateFavoriteAuthors(authors: List<Author>)

    suspend fun updateFavoriteGenres(genres: List<Genre>)

    suspend fun updateFavoriteBooks(books: List<GoogleBook>)

    suspend fun updateWantToReadBooks(books: List<GoogleBook>)
}