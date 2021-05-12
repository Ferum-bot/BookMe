package com.levit.book_me.repositories.interfaces

import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface SearchBooksRepository {

    val books: SharedFlow<RetrofitResult<List<GoogleBook>>>

    suspend fun searchBooks(queryParams: GoogleBooksVolumeParameters)

    suspend fun searchPopularBooks(queryParams: GoogleBooksVolumeParameters)

    suspend fun searchMostChosenBooks(queryParams: GoogleBooksVolumeParameters)
}