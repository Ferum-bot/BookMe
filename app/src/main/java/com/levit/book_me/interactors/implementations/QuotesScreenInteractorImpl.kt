package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core.models.GoQuotesParameters
import com.levit.book_me.core.models.GoQuotesTypes
import com.levit.book_me.interactors.interfaces.QuotesScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.GoQuotesQuoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesScreenInteractorImpl @Inject constructor(
    private val repository: GoQuotesQuoteRepository
): QuotesScreenInteractor {

    companion object {
        private const val QUOTE_NUMBER = 100
    }

    override val quotes: Flow<RetrofitResult<List<GoQuote>>>
        get() = repository.quotes

    override suspend fun searchQuotes(type: GoQuotesTypes, typeQuery: String) {
        val params = GoQuotesParameters(
            type = type,
            valueToSearch = typeQuery,
            resultCount = QUOTE_NUMBER
        )
        repository.getAllQuotesWithParams(params)
    }
}