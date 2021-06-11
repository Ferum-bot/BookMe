package com.levit.book_me.interactors.creating_profile

import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface SearchBooksInteractor {

    val books: Flow<RetrofitResult<List<GoogleBook>>>

    suspend fun searchBooks(query: String)

    suspend fun getPopularBooks()
    suspend fun getMostChosenBooks()
}