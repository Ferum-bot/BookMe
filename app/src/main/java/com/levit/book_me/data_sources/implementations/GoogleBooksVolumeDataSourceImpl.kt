package com.levit.book_me.data_sources.implementations

import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.network.exceptions.GoogleBooksException
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.GoogleBooksResponse
import com.levit.book_me.network.response_models.GoogleBooksResponseError
import com.levit.book_me.network.services.GoogleBooksService
import com.levit.book_me.network.utill.NetworkConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class GoogleBooksVolumeDataSourceImpl @Inject constructor(
    private val googleBooksService: GoogleBooksService
): GoogleBooksVolumeDataSource {

    private val _searchResult = MutableSharedFlow<RetrofitResult<GoogleBooksResponse>>()
    override val searchResult: SharedFlow<RetrofitResult<GoogleBooksResponse>>
        get() = _searchResult

    @ExperimentalCoroutinesApi
    override suspend fun searchVolumes(parameters: GoogleBooksVolumeParameters) {
        val paramsMap = parameters.toMap()
        val queryResult = googleBooksService.searchGoogleBooks(paramsMap)
                .parseRowResponse()
        _searchResult.resetReplayCache()
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