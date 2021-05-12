package com.levit.book_me.data_sources.interfaces

import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.google_books.GoogleBooksResponse
import kotlinx.coroutines.flow.SharedFlow

interface GoogleBooksVolumeDataSource {

    val searchResult: SharedFlow<RetrofitResult<GoogleBooksResponse>>

    suspend fun searchVolumes(parameters: GoogleBooksVolumeParameters)

    suspend fun searchPopularVolumes(parameters: GoogleBooksVolumeParameters)

    suspend fun searchMostChosenVolumes(parameters: GoogleBooksVolumeParameters)
}