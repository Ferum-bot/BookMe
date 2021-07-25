package com.levit.book_me.interactors.quotes.impl

import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuotesTypes
import com.levit.book_me.interactors.quotes.QuotesScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.guotes.QuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesScreenInteractorImpl @Inject constructor(
    private val repository: QuotesRepository
): QuotesScreenInteractor {

    override val quotes: Flow<RetrofitResult<List<QuoteModel>>>
    get() = repository.quotes

    override suspend fun searchQuotes(type: QuotesTypes, typeQuery: String) {
        when(type) {
            QuotesTypes.AUTHOR -> repository.getAllQuotesFromAuthor(typeQuery)
            QuotesTypes.TAG -> repository.getAllQuotesFromTag(typeQuery)
        }
    }
}