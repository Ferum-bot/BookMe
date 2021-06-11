package com.levit.book_me.data_sources.profile.impl

import android.content.SharedPreferences
import com.google.gson.Gson
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.models.google_books.GoogleBook
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Named

class SharedPrefProfileDataSource @Inject constructor(

    @Named(DIConstants.PROFILE_SHARED_PREFERENCE_NAME)
    private val storage: SharedPreferences,
): CacheProfileDataSource {

    companion object {

        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 0

        private const val NAME_FIELD = "name_field"
        private const val SURNAME_FIELD = "surname_field"
        private const val WORDS_ABOUT_PERSON_FIELD = "words_about_person_field"
        private const val PROFILE_PHOTO_URL_FIELD = "profile_photo_url_field"
        private const val FAVORITE_GENRES_FIELD = "favorite_genres_field"
        private const val FAVORITE_AUTHORS_FIELD = "favorite_authors_filed"
        private const val FAVORITE_BOOKS_FIELD = "favorite_books_field"
        private const val WANT_TO_READ_BOOKS_FIELD = "want_to_read_books_field"
        private const val QUOTE_FIELD = "quote_field"
    }

    private val _profile: MutableSharedFlow<ProfileModel> = MutableSharedFlow(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val profile: SharedFlow<ProfileModel> = _profile

    private val gson = Gson()

    override suspend fun getProfile() {
        val name = getName()
        val surname = getSurname()
        val wordsAboutPerson = getWordsAboutPerson()
        val profilePhotoUrl = getProfilePhotoUrl()
        val favoriteGenres = getFavoriteGenres()
        val favoriteAuthors = getFavoriteAuthors()
        val favoriteBooks = getFavoriteBooks()
        val wantToReadBooks = getWantToReadBooks()
        val quote = getQuote()

        val profileModel = ProfileModel(
            name, surname, wordsAboutPerson, profilePhotoUrl, favoriteGenres,
            favoriteAuthors, favoriteBooks, wantToReadBooks, quote
        )
        _profile.emit(profileModel)
    }

    override suspend fun safeProfile(profile: ProfileModel) {
        safeName(profile.name)
        safeSurname(profile.surname)
        safeWordsAboutPerson(profile.wordsAboutPerson)
        safeProfilePhotoUrl(profile.profilePhotoUrl)
        safeFavoriteGenres(profile.favouriteGenres)
        safeFavoriteAuthors(profile.favouriteAuthors)
        safeFavoriteBooks(profile.favouriteBooks)
        safeWantToReadBooks(profile.wantToReadBooks)
        safeQuote(profile.quote)
    }

    private fun getName(): String {
        return storage.getString(NAME_FIELD, "") ?: ""
    }

    private fun safeName(name: String) {
        with(storage.edit()) {
            putString(NAME_FIELD, name)
            apply()
        }
    }

    private fun getSurname(): String {
        return storage.getString(SURNAME_FIELD, "") ?: ""
    }

    private fun safeSurname(surname: String) {
        with(storage.edit()) {
            putString(SURNAME_FIELD, surname)
            apply()
        }
    }

    private fun getWordsAboutPerson(): String {
        return storage.getString(WORDS_ABOUT_PERSON_FIELD, "") ?: ""
    }

    private fun safeWordsAboutPerson(words: String) {
        with(storage.edit()) {
            putString(WORDS_ABOUT_PERSON_FIELD, words)
            apply()
        }
    }

    private fun getProfilePhotoUrl(): String {
        return storage.getString(PROFILE_PHOTO_URL_FIELD, "") ?: ""
    }

    private fun safeProfilePhotoUrl(url: String) {
        with(storage.edit()) {
            putString(PROFILE_PHOTO_URL_FIELD, url)
            apply()
        }
    }

    private fun getFavoriteGenres(): List<Genre> {
        return getValueFromGson(FAVORITE_GENRES_FIELD, emptyList())
    }

    private fun safeFavoriteGenres(genres: List<Genre>) {
        val rawString = gson.toJson(genres).toString()
        with(storage.edit()) {
            putString(FAVORITE_GENRES_FIELD, rawString)
            apply()
        }
    }

    private fun getFavoriteAuthors(): List<Author> {
        return getValueFromGson(FAVORITE_AUTHORS_FIELD, emptyList())
    }

    private fun safeFavoriteAuthors(authors: List<Author>) {
        val rawString = gson.toJson(authors).toString()
        with(storage.edit()) {
            putString(FAVORITE_AUTHORS_FIELD, rawString)
            apply()
        }
    }

    private fun getFavoriteBooks(): List<GoogleBook> {
        return getValueFromGson(FAVORITE_BOOKS_FIELD, emptyList())
    }

    private fun safeFavoriteBooks(books: List<GoogleBook>) {
        val rawString = gson.toJson(books).toString()
        with(storage.edit()) {
            putString(FAVORITE_BOOKS_FIELD, rawString)
            apply()
        }
    }

    private fun getWantToReadBooks(): List<GoogleBook> {
        return getValueFromGson(WANT_TO_READ_BOOKS_FIELD, emptyList())
    }

    private fun safeWantToReadBooks(books: List<GoogleBook>) {
        val rawString = gson.toJson(books).toString()
        with(storage.edit()) {
            putString(WANT_TO_READ_BOOKS_FIELD, rawString)
            apply()
        }
    }

    private fun getQuote(): GoQuote {
        return getValueFromGson(QUOTE_FIELD, GoQuote())
    }

    private fun safeQuote(quote: GoQuote) {
        val rawString = gson.toJson(quote).toString()
        with(storage.edit()) {
            putString(QUOTE_FIELD, rawString)
            apply()
        }
    }

    private fun <T: Any> getValueFromGson(fieldName: String, defValue: T): T {
        val rawString = storage.getString(fieldName, null)
            ?: return defValue
        val type = object: TypeTokenAdapter<T>() {}.adaptedType
        return try {
            val result = gson.fromJson<T>(rawString, type)
            result
        } catch (ex: Exception) {
            defValue
        }
    }
}