package com.levit.book_me.interactors.quotes.impl

import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.interactors.quotes.QuotesTagsScreenInterator
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.guotes.QuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesTagsScreenInteratorImpl @Inject constructor(
    private val repository: QuotesRepository
): QuotesTagsScreenInterator {

    override val tags: Flow<RetrofitResult<List<QuoteTagModel>>>
    get() = repository.tags

    override suspend fun getAllTags() {
        repository.getAllTags()
    }
}