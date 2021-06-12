package com.levit.book_me.data_sources.profile.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.data_sources.profile.RegisterNewUserDataSource
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class FirestoreRegisterNewUserDataSource @Inject constructor(
    private val remote:  FirebaseFirestore,
    private val auth: FirebaseAuth,
): RegisterNewUserDataSource {

    companion object {

        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 0

        private const val USERS = "users"
        private const val BASE_COLLECTIONS = "baseInformation"

        private const val FAVORITE_AUTHORS_DOCUMENT = "FavoriteAuthors"
        private const val FAVORITE_BOOKS_DOCUMENT = "FavoriteBooks"
        private const val WANT_TO_READ_BOOKS_DOCUMENT = "WantToReadBooks"
        private const val GENRES_DOCUMENT = "GENRES"
        private const val QUOTE_DOCUMENT = "Quote"

        private const val FAVORITE_AUTHOR_COLLECTION = "ChosenAuthors"
        private const val FAVORITE_BOOKS_COLLECTION = "ChosenBooks"
        private const val WANT_TO_READ_BOOKS_COLLECTION = "ChosenBooks"
        private const val GENRES_COLLECTION = "ChosenGenres"

        private const val AUTHOR_PREF_NAME = "author"
        private const val BOOK_PREF_NAME = "book"
        private const val GENRE_PREF_NAME = "genre"

        private const val UPLOAD_DATA_COUNT = 6
    }

    private val _resultStatus: MutableSharedFlow<RetrofitResult<UserResponseModel>> = MutableSharedFlow(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val resultStatus: SharedFlow<RetrofitResult<UserResponseModel>> = _resultStatus

    private val userId = auth.currentUser?.uid ?: ""

    private val userBaseInfoRef = remote.collection(USERS).document(userId)
    private val userCollectionsRef = userBaseInfoRef.collection(BASE_COLLECTIONS)

    private val userFavoriteAuthorsCollectionRef = userCollectionsRef
        .document(FAVORITE_AUTHORS_DOCUMENT).collection(FAVORITE_AUTHOR_COLLECTION)
    private val userFavoriteBooksCollectionRef = userCollectionsRef
        .document(FAVORITE_BOOKS_DOCUMENT).collection(FAVORITE_BOOKS_COLLECTION)
    private val userWantToReadBooksCollectionRef = userCollectionsRef
        .document(WANT_TO_READ_BOOKS_DOCUMENT).collection(WANT_TO_READ_BOOKS_COLLECTION)
    private val userFavoriteGenresCollectionRef = userCollectionsRef
        .document(GENRES_DOCUMENT).collection(GENRES_COLLECTION)

    private val userQuoteDocument = userCollectionsRef.document(QUOTE_DOCUMENT)

    private var numberOfUploadedData: AtomicInteger = AtomicInteger(0)
    private var isErrorAlreadyGot = false

    override suspend fun registerNewUser(profileInformation: ProfileModel) {
        isErrorAlreadyGot = false
        numberOfUploadedData = AtomicInteger(0)

        uploadBaseInformation(profileInformation)
        uploadFavoriteAuthors(profileInformation)
        uploadFavoriteBooks(profileInformation)
        uploadWantToReadBooks(profileInformation)
        uploadFavoriteGenres(profileInformation)
        uploadQuote(profileInformation)
    }

    private fun uploadBaseInformation(profile: ProfileModel) {
        val baseInfo = profile.getBaseInfoMap()
        userBaseInfoRef.set(baseInfo)
            .addOnSuccessListener{ onSuccessLoaded() }
            .addOnFailureListener { onFailureLoaded(it) }
    }

    private fun uploadFavoriteAuthors(profile: ProfileModel) {
        val authors = profile.favouriteAuthors
        authors.forEachIndexed { index, author ->
            userFavoriteAuthorsCollectionRef
                .document("$AUTHOR_PREF_NAME$index")
                .set(author.toMap())
                .addOnSuccessListener { onSuccessLoaded() }
                .addOnFailureListener { onFailureLoaded(it) }
        }
    }

    private fun uploadFavoriteBooks(profile: ProfileModel) {
        val books = profile.favouriteBooks
        books.forEachIndexed { index, book ->
            userFavoriteBooksCollectionRef
                .document("$BOOK_PREF_NAME$index")
                .set(book.toMap())
                .addOnSuccessListener { onSuccessLoaded() }
                .addOnFailureListener { onFailureLoaded(it) }
        }
    }

    private fun uploadWantToReadBooks(profile: ProfileModel) {
        val books = profile.wantToReadBooks
        books.forEachIndexed { index, book ->
            userWantToReadBooksCollectionRef
                .document("$BOOK_PREF_NAME$index")
                .set(book.toMap())
                .addOnSuccessListener { onSuccessLoaded() }
                .addOnFailureListener { onFailureLoaded(it) }
        }
    }

    private fun uploadFavoriteGenres(profile: ProfileModel) {
        val genres = profile.favouriteGenres
        genres.forEachIndexed { index, genre ->
            userFavoriteGenresCollectionRef
                .document("$GENRE_PREF_NAME$index")
                .set(genre.toMap())
                .addOnSuccessListener { onSuccessLoaded() }
                .addOnFailureListener { onFailureLoaded(it) }
        }
    }

    private fun uploadQuote(profile: ProfileModel) {
        val quote = profile.quote
        userQuoteDocument
            .set(quote.toMap())
            .addOnSuccessListener { onSuccessLoaded() }
            .addOnFailureListener { onFailureLoaded(it) }
    }

    private fun onSuccessLoaded() = runBlocking {
        if (isErrorAlreadyGot) {
            return@runBlocking
        }
        val currentNumber = numberOfUploadedData.incrementAndGet()
        if (currentNumber == UPLOAD_DATA_COUNT) {
            emitSuccessResult()
        }
    }

    private fun onFailureLoaded(ex: Exception) = runBlocking {
        if (isErrorAlreadyGot) {
            return@runBlocking
        }
        isErrorAlreadyGot = true
        val result = RetrofitResult.Failure.Error(ex)
        _resultStatus.emit(result)
    }

    private suspend fun emitSuccessResult() {
        val response = UserResponseModel(
            statusMessage = "Status OK",
            statusCode = 200
        )
        val result = RetrofitResult.Success.Value(response)
        _resultStatus.emit(result)
    }

    private fun ProfileModel.getBaseInfoMap(): Map<String, String>
    = mapOf(
        "name" to name,
        "surname" to surname,
        "wordsAboutPerson" to wordsAboutPerson,
        "profileImageUrl" to profilePhotoUrl,
    )

    private fun GoQuote.toMap(): Map<String, String>
    = mapOf(
        "text" to text,
        "authorFullName" to authorFullName,
        "tag" to tag,
    )

    private fun Author.toMap(): Map<String, String>
    = mapOf(
        "fullName" to fullName,
    )

    private fun GoogleBook.toMap(): Map<String, String>
    = mapOf(
        "title" to title,
        "authors" to (listOfAuthors?.joinToString() ?: ""),
        "imageLink" to (imageLinks?.getBiggestAvailableLink() ?: "")
    )

    private fun Genre.toMap(): Map<String, String>
    = mapOf(
        "string" to string,
        "isBig" to isBig.toString(),
    )
}