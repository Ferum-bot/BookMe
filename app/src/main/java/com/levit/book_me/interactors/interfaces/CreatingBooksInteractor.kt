package com.levit.book_me.interactors.interfaces

import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface CreatingBooksInteractor {

    val books: Flow<RetrofitResult<List<GoogleBook>>>

    suspend fun getPopularBooks()
    suspend fun getMostChosenBooks()
}