package com.levit.book_me.data_sources.quotes

import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface QuotesTagDataSource {

    val tags: SharedFlow<RetrofitResult<List<QuoteTagModel>>>

    suspend fun getAllTags()
}