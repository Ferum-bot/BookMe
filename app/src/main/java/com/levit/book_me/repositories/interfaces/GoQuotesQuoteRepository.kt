package com.levit.book_me.repositories.interfaces

import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core.models.GoQuotesParameters
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface GoQuotesQuoteRepository {

    val quotes: SharedFlow<RetrofitResult<List<GoQuote>>>

    suspend fun getRandomQuotes(count: Int)

    suspend fun getRandomQuotesWithParams(params: GoQuotesParameters)

    suspend fun getAllQuotesWithParams(params: GoQuotesParameters)
}