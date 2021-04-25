package com.levit.book_me.interactors.interfaces

import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.core.models.GoQuotesParameters
import com.levit.book_me.core.models.GoQuotesTypes
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesScreenInteractor {

    val quotes: Flow<RetrofitResult<List<GoQuote>>>

    suspend fun searchQuotes(type: GoQuotesTypes, typeQuery: String)
}