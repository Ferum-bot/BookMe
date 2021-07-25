package com.levit.book_me.interactors.quotes

import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuotesTypes
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesScreenInteractor {

    val quotes: Flow<RetrofitResult<List<QuoteModel>>>

    suspend fun searchQuotes(type: QuotesTypes, typeQuery: String)
}