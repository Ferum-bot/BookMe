package com.levit.book_me.interactors.quotes.impl

import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.interactors.quotes.QuotesAuthorsScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.guotes.QuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesAuthorsScreenInteractorImpl @Inject constructor(
    private val repository: QuotesRepository
): QuotesAuthorsScreenInteractor {

    override val authors: Flow<RetrofitResult<List<QuoteAuthorModel>>>
    get() = repository.authors

    override suspend fun getAllAuthors() {
        repository.getAllAuthors()
    }
}