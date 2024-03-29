package com.levit.book_me.data_sources.profile.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.levit.book_me.core.extensions.getBaseInfoMap
import com.levit.book_me.core.extensions.toMap
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.core.utill.FirebaseConstants
import com.levit.book_me.data_sources.firebase.FirebaseDataSourceReferences.userBaseInfoRef
import com.levit.book_me.data_sources.firebase.FirebaseDataSourceReferences.userFavoriteAuthorsCollectionRef
import com.levit.book_me.data_sources.firebase.FirebaseDataSourceReferences.userFavoriteBooksCollectionRef
import com.levit.book_me.data_sources.firebase.FirebaseDataSourceReferences.userFavoriteGenresCollectionRef
import com.levit.book_me.data_sources.firebase.FirebaseDataSourceReferences.userQuoteDocument
import com.levit.book_me.data_sources.firebase.FirebaseDataSourceReferences.userWantToReadBooksCollectionRef
import com.levit.book_me.data_sources.profile.RegisterNewUserDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

/**
 * I know that this is awful code, but firebase does not have
 * comfortable API to connect with, so lately we will migrate to
 * own backend server.
 */
class FirestoreRegisterNewUserDataSource @Inject constructor(
    private val remote:  FirebaseFirestore,
    private val auth: FirebaseAuth,
): RegisterNewUserDataSource {

    companion object {

        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 0

        private const val UPLOAD_DATA_COUNT = 6
    }

    private val _resultStatus: MutableSharedFlow<RetrofitResult<UserResponseModel>> = MutableSharedFlow(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val resultStatus: SharedFlow<RetrofitResult<UserResponseModel>> = _resultStatus

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
                .document(FirebaseConstants.getAuthorDocumentPath(index))
                .set(author.toMap())
                .addOnSuccessListener { onSuccessLoaded() }
                .addOnFailureListener { onFailureLoaded(it) }
        }
    }

    private fun uploadFavoriteBooks(profile: ProfileModel) {
        val books = profile.favouriteBooks
        books.forEachIndexed { index, book ->
            userFavoriteBooksCollectionRef
                .document(FirebaseConstants.getBookDocumentPath(index))
                .set(book.toMap())
                .addOnSuccessListener { onSuccessLoaded() }
                .addOnFailureListener { onFailureLoaded(it) }
        }
    }

    private fun uploadWantToReadBooks(profile: ProfileModel) {
        val books = profile.wantToReadBooks
        books.forEachIndexed { index, book ->
            userWantToReadBooksCollectionRef
                .document(FirebaseConstants.getBookDocumentPath(index))
                .set(book.toMap())
                .addOnSuccessListener { onSuccessLoaded() }
                .addOnFailureListener { onFailureLoaded(it) }
        }
    }

    private fun uploadFavoriteGenres(profile: ProfileModel) {
        val genres = profile.favouriteGenres
        genres.forEachIndexed { index, genre ->
            userFavoriteGenresCollectionRef
                .document(FirebaseConstants.getGenreDocumentPath(index))
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
}