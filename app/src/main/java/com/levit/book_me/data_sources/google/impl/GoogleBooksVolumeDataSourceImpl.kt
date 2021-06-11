package com.levit.book_me.data_sources.google.impl

import com.levit.book_me.core.models.google_books.GoogleBooksVolumeParameters
import com.levit.book_me.data_sources.google.GoogleBooksVolumeDataSource
import com.levit.book_me.network.exceptions.GoogleBooksException
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.google_books.GoogleBooksResponse
import com.levit.book_me.network.response_models.google_books.GoogleBooksResponseError
import com.levit.book_me.network.services.GoogleBooksService
import com.levit.book_me.network.utill.NetworkConstants
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GoogleBooksVolumeDataSourceImpl @Inject constructor(
    private val googleBooksService: GoogleBooksService
): GoogleBooksVolumeDataSource {

    companion object {
        private const val REPLAY_NUMBER = 1
        private const val CAPACITY_SIZE = 1
    }

    private val _searchResult = MutableSharedFlow<RetrofitResult<GoogleBooksResponse>>(
        replay = REPLAY_NUMBER,
        extraBufferCapacity = CAPACITY_SIZE,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val searchResult: SharedFlow<RetrofitResult<GoogleBooksResponse>>
        get() = _searchResult

    override suspend fun searchVolumes(parameters: GoogleBooksVolumeParameters) {
        val paramsMap = parameters.toMap()
        val queryResult = googleBooksService.searchGoogleBooks(paramsMap)
                .parseRowResponse()
        _searchResult.emit(queryResult)
    }

    override suspend fun searchPopularVolumes(parameters: GoogleBooksVolumeParameters) {
        val paramsMap = parameters.toMap()
        val queryResult = googleBooksService.searchPopularBooks(paramsMap)
                .parseRowResponse()
        _searchResult.emit(queryResult)
    }

    override suspend fun searchMostChosenVolumes(parameters: GoogleBooksVolumeParameters) {
        val paramsMap = parameters.toMap()
        val queryResult = googleBooksService.searchMostChosenBooks(paramsMap)
                .parseRowResponse()
        _searchResult.emit(queryResult)
    }

    private fun RetrofitResult<GoogleBooksResponse>.parseRowResponse(): RetrofitResult<GoogleBooksResponse> {
        if (this !is RetrofitResult.Success) {
            return this as RetrofitResult.Failure<*>
        }
        val response = data
        if (response.errorResponse == null) {
            return RetrofitResult.Success.Value(response)
        }
        return response.errorResponse.parseToErrorResult()
    }

    private fun GoogleBooksResponseError.parseToErrorResult(): RetrofitResult.Failure.Error {
        val exception = GoogleBooksException(
            statusCode = errorStatusCode,
            statusMessage = errorStatus,
            queryUrl = NetworkConstants.GOOGLE_BOOKS_API_BASE_URL + "volumes",
            errorMessage = errorMessage,
            errors = listOfErrors
        )

        return RetrofitResult.Failure.Error(exception, errorMessage)
    }
}