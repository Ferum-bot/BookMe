package com.levit.book_me.interactors.interfaces

import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface CreatingBooksInteractor {

    val popularBooks: Flow<RetrofitResult<List<GoogleBook>>>

    val mostChosenBooks: Flow<List<RetrofitResult<GoogleBook>>>

    suspend fun getPopularBooks()

    suspend fun getMostChosenBooks()
}