package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.GoQuotesTag
import com.levit.book_me.interactors.interfaces.QuotesTagsScreenInterator
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.GoQuotesTagRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesTagsScreenInteratorImpl @Inject constructor(
    private val repository: GoQuotesTagRepository
): QuotesTagsScreenInterator {

    override val tags: Flow<RetrofitResult<List<GoQuotesTag>>>
        get() = repository.tags

    override suspend fun getAllTags() {
        repository.getAllTags()
    }
}