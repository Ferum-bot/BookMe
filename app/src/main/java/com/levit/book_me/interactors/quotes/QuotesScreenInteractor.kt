package com.levit.book_me.interactors.quotes

import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core.models.GoQuotesTypes
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesScreenInteractor {

    val quotes: Flow<RetrofitResult<List<GoQuote>>>

    suspend fun searchQuotes(type: GoQuotesTypes, typeQuery: String)
}