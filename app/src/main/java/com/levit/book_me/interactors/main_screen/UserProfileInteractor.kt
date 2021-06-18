package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.interactors.creating_profile.UploadProfileImageInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow

interface UserProfileInteractor: UploadProfileImageInteractor {

    val profileModel: Flow<BaseRepositoryResult<ProfileModel>>

    suspend fun getProfile()

    suspend fun updateName(newName: String)

    suspend fun updateSurname(surname: String)

    suspend fun updateWordsAboutPerson(wordsAboutPerson: String)

    suspend fun updateQuote(quote: GoQuote)

    suspend fun updateFavoriteAuthors(authors: List<Author>)

    suspend fun updateFavoriteGenres(genres: List<Genre>)

    suspend fun updateFavoriteBooks(books: List<GoogleBook>)

    suspend fun updateWantToReadBooks(books: List<GoogleBook>)
}