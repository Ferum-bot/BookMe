package com.levit.book_me.data_sources.implementations

import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.GoogleBooksResponse
import com.levit.book_me.network.services.GoogleBooksService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GoogleBooksVolumeDataSourceImpl @Inject constructor(
    private val googleBooksService: GoogleBooksService
): GoogleBooksVolumeDataSource {

    private val _searchResult = MutableSharedFlow<RetrofitResult<GoogleBooksResponse>>()
    override val searchResult: SharedFlow<RetrofitResult<GoogleBooksResponse>>
        get() = _searchResult

    override suspend fun searchVolumes(parameters: GoogleBooksVolumeParameters) {
        val paramsMap = parameters.toMap()
        val queryResult = googleBooksService.searchGoogleBooks(paramsMap)
        _searchResult.emit(queryResult)
    }
}