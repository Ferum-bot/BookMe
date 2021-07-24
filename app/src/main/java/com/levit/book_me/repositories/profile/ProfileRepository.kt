package com.levit.book_me.repositories.profile

import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.SharedFlow

interface ProfileRepository
    : FirebaseStorageUploadUriRepository{

    val resultProfile: SharedFlow<BaseRepositoryResult<ProfileModel>>

    suspend fun getProfile()

    suspend fun deleteProfile()

    /**
     * If property is null, data source will not update this property.
     */
    suspend fun updateBaseInformation(
        name: String? = null, surname: String? = null, wordsAboutPerson: String? = null
    )

    suspend fun updateQuote(quote: QuoteModel)

    suspend fun updateFavoriteAuthors(authors: List<Author>)

    suspend fun updateFavoriteGenres(genres: List<Genre>)

    suspend fun updateFavoriteBooks(books: List<GoogleBook>)

    suspend fun updateWantToReadBooks(books: List<GoogleBook>)
}