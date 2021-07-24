package com.levit.book_me.interactors.quotes

import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesTagsScreenInterator {

    val tags: Flow<RetrofitResult<List<QuoteTagModel>>>

    suspend fun getAllTags()
}