package com.levit.book_me.repositories.profile.impl

import android.net.Uri
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.data_sources.profile.ProfileRemoteDataSourceFacade
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.profile.ProfileRepository
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class ProfileRepositoryImpl @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineContext: CoroutineContext,

    private val localDataSource: CacheProfileDataSource,
    private val remoteDataSource: ProfileRemoteDataSourceFacade,
): ProfileRepository {

    companion object {

        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 0
    }

    private enum class Actions {
        GET_PROFILE, DELETE_PROFILE, UPDATE_PROFILE, WAITING;
    }

    private val repositoryScope = CoroutineScope(coroutineContext)

    @Volatile
    private var currentAction = Actions.WAITING

    private val _resultProfile: MutableSharedFlow<BaseRepositoryResult<ProfileModel>>
    = MutableSharedFlow(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val resultProfile: SharedFlow<BaseRepositoryResult<ProfileModel>>
    = _resultProfile

    override val uploadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>
    = remoteDataSource.uploadToFirebaseStorageResult

    init {
        repositoryScope.launch {
            remoteDataSource.remoteProfile.collect { result ->
                handleProfileRemoteResult(result)
            }
        }

        repositoryScope.launch {
            localDataSource.profile.collect { result ->
                handleProfileCacheResult(result)
            }
        }
    }

    override suspend fun getProfile() = withContext(coroutineContext) {
        currentAction = Actions.GET_PROFILE
        remoteDataSource.getProfile()
    }

    override suspend fun deleteProfile() = withContext(coroutineContext) {
        currentAction = Actions.DELETE_PROFILE
        remoteDataSource.deleteProfile()
    }

    override suspend fun upLoadUriToFirebaseStorage(uri: Uri)
    = withContext(coroutineContext) {
        remoteDataSource.upLoadUriToFirebaseStorage(uri)
    }

    override suspend fun updateBaseInformation(name: String?, surname: String?, wordsAboutPerson: String?)
    = withContext(coroutineContext) {
        currentAction = Actions.UPDATE_PROFILE
        remoteDataSource.updateBaseInformation(name, surname, wordsAboutPerson)
    }

    override suspend fun updateFavoriteAuthors(authors: List<Author>)
    = withContext(coroutineContext) {
        currentAction = Actions.UPDATE_PROFILE
        remoteDataSource.updateFavoriteAuthors(authors)
    }

    override suspend fun updateFavoriteBooks(books: List<GoogleBook>)
    = withContext(coroutineContext) {
        currentAction = Actions.UPDATE_PROFILE
        remoteDataSource.updateFavoriteBooks(books)
    }

    override suspend fun updateFavoriteGenres(genres: List<Genre>)
    = withContext(coroutineContext) {
        currentAction = Actions.UPDATE_PROFILE
        remoteDataSource.updateFavoriteGenres(genres)
    }

    override suspend fun updateQuote(quote: GoQuote) = withContext(coroutineContext) {
        currentAction = Actions.UPDATE_PROFILE
        remoteDataSource.updateQuote(quote)
    }

    override suspend fun updateWantToReadBooks(books: List<GoogleBook>)
    = withContext(coroutineContext) {
        currentAction = Actions.UPDATE_PROFILE
        remoteDataSource.updateWantToReadBooks(books)
    }

    private suspend fun handleProfileRemoteResult(result: RetrofitResult<ProfileModel>) {
        if (result is RetrofitResult.Success) {
            val profile = result.data
            val repositoryResult = BaseRepositoryResult.RemoteResult(result)
            localDataSource.safeProfile(profile)
            _resultProfile.emit(repositoryResult)
            return
        }
        handleErrorRemoteResult(result as RetrofitResult.Failure<*>)
    }

    private suspend fun handleProfileCacheResult(result: ProfileModel) {
        val cacheResult = BaseRepositoryResult.CacheResult(result)
        _resultProfile.emit(cacheResult)
    }

    private suspend fun handleErrorRemoteResult(result: RetrofitResult.Failure<*>)
    = when(currentAction) {
        Actions.GET_PROFILE -> handleGetProfileRemoteError(result)
        Actions.UPDATE_PROFILE -> handleUpdateProfileRemoteError(result)
        Actions.DELETE_PROFILE -> handleDeleteProfileRemoteError(result)
        Actions.WAITING -> handleWaiting()
    }.also {
        currentAction = Actions.WAITING
    }

    private suspend fun handleGetProfileRemoteError(result: RetrofitResult.Failure<*>) {
        var throwable: Throwable? = result.error
        var message: String? = null
        var messageId: Int? = null
        var statusCode: Int? = null

        when (result) {
            is RetrofitResult.Failure.Error -> {
                throwable = result.error
                message = result.message
                messageId = result.messageId
            }
            is RetrofitResult.Failure.HttpError -> {
                throwable = result.error
                message = result.statusMessage
                statusCode = result.statusCode
            }
        }

        val cacheProfile = localDataSource.getProfile()
        val cacheResult =  BaseRepositoryResult.CacheResult(
            result = cacheProfile,
            exception = throwable,
            errorMessage = message,
            errorMessageId = messageId,
            statusCode = statusCode,
        )
        _resultProfile.emit(cacheResult)
    }

    private suspend fun handleUpdateProfileRemoteError(result: RetrofitResult.Failure<*>) {
        handleGetProfileRemoteError(result)
    }

    private suspend fun handleDeleteProfileRemoteError(result: RetrofitResult.Failure<*>) {
        handleGetProfileRemoteError(result)
    }

    private suspend fun handleWaiting() {

    }
}