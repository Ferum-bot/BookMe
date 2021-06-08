package com.levit.book_me.data_sources.interfaces

import com.levit.book_me.core.models.GoQuotesParameters
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.GoQuotesQuoteResponse
import kotlinx.coroutines.flow.SharedFlow

interface GoQuotesQuoteDataSource {

    val data: SharedFlow<RetrofitResult<GoQuotesQuoteResponse>>

    suspend fun getRandomQuotes(numberOfQuotes: Int)

    suspend fun getRandomQuotesWithParams(params: GoQuotesParameters)

    suspend fun getAllQuotesWithParams(params: GoQuotesParameters)
}