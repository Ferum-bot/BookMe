package com.levit.book_me.interactors.interfaces

import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesMainScreenInteractor {

    val numberOfTags: Flow<RetrofitResult<Int>>
    val numberOfAuthors: Flow<RetrofitResult<Int>>

    val randomQuotes: Flow<RetrofitResult<List<GoQuote>>>

    suspend fun getNumberOfTags()
    suspend fun getNumberOfAuthors()

    suspend fun getRandomQuotes()
}