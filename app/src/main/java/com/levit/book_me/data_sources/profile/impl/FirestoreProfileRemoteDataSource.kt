package com.levit.book_me.data_sources.profile.impl

import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.data_sources.firebase.FirebaseDataSourceReferences
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * I know that this is awful code, but firebase does not have
 * comfortable API to connect with, so lately we will migrate to
 * ovn backend server.
 */
class FirestoreProfileRemoteDataSource @Inject constructor(

): BaseProfileRemoteDataSource {

    companion object {

        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 0
    }

    private val _remoteProfile: MutableSharedFlow<RetrofitResult<ProfileModel>> = MutableSharedFlow(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val remoteProfile: SharedFlow<RetrofitResult<ProfileModel>> = _remoteProfile

    private var isErrorGet = false

    override suspend fun getProfile() {
        isErrorGet = false

        val name = getName()
        val surname = getSurname()
        val wordsAboutPerson = getWordsAboutPerson()
        val profilePhotoUrl = getProfilePhotoUrl()
        val favoriteGenres = getFavoriteGenres()
        val favoriteAuthors = getFavoriteAuthors()
        val favoriteBooks = getFavoriteBooks()
        val wantToReadBooks = getWantToReadBooks()
        val quote = getQuote()

        val profile = ProfileModel(
            name = name,
            surname = surname,
            wordsAboutPerson = wordsAboutPerson,
            profilePhotoUrl = profilePhotoUrl,
            favouriteGenres = favoriteGenres,
            favouriteAuthors = favoriteAuthors,
            favouriteBooks = favoriteBooks,
            wantToReadBooks = wantToReadBooks,
            quote = quote,
        )
        val result = RetrofitResult.Success.Value(profile)

        _remoteProfile.emit(result)
    }

    override suspend fun deleteProfile() {
        val exception = Exception("Not supported yet")
        _remoteProfile.emit(RetrofitResult.Failure.Error(exception))
    }

    override suspend fun updateBaseInformation(name: String?, surname: String?, wordsAboutPerson: String?) {
        isErrorGet = false

        if (name != null) {
            updateName(name)
        }
        if (surname != null) {
            updateSurname(surname)
        }
        if (wordsAboutPerson != null) {
            updateWordsAboutPerson(wordsAboutPerson)
        }
    }

    override suspend fun updateFavoriteAuthors(authors: List<Author>) {
        isErrorGet = false
    }

    override suspend fun updateFavoriteGenres(genres: List<Genre>) {
        isErrorGet = false
    }

    override suspend fun updateQuote(quote: GoQuote) {
        isErrorGet = false
    }

    override suspend fun updateFavoriteBooks(books: List<GoogleBook>) {
        isErrorGet = false
    }

    override suspend fun updateWantToReadBooks(books: List<GoogleBook>) {
        isErrorGet = false
    }

    private fun onFailureListener(exception: Exception) = runBlocking {
        if (isErrorGet) {
            return@runBlocking
        }
        isErrorGet = true

        val result = RetrofitResult.Failure.Error(exception)
        _remoteProfile.emit(result)
    }

    private suspend fun updateName(name: String) {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.update("name", name)
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { fieldUpdated() }
    }

    private suspend fun getName(): String {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.get()
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { document ->
                val name = document.get("name") as? String

            }
    }

    private suspend fun updateSurname(surname: String) {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.update("surname", surname)
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { fieldUpdated() }
    }

    private suspend fun getSurname(): String {

    }

    private suspend fun getProfilePhotoUrl(): String {

    }

    private suspend fun updateWordsAboutPerson(wordsAboutPerson: String) {
        val ref = FirebaseDataSourceReferences.userBaseInfoRef
        ref.update("wordsAboutPerson", wordsAboutPerson)
            .addOnFailureListener { onFailureListener(it) }
            .addOnSuccessListener { fieldUpdated() }
    }

    private suspend fun getWordsAboutPerson(): String {

    }

    private suspend fun getFavoriteGenres(): List<Genre> {

    }

    private suspend fun getFavoriteAuthors(): List<Author> {

    }

    private suspend fun getFavoriteBooks(): List<GoogleBook> {

    }

    private suspend fun getWantToReadBooks(): List<GoogleBook> {

    }

    private suspend fun getQuote(): GoQuote {

    }

    private fun fieldUpdated() = runBlocking {
        getProfile()
    }
}