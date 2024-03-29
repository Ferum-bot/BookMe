package com.levit.book_me.data_sources.quotes

import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface QuotesAuthorDataSource {

    val authors: SharedFlow<RetrofitResult<List<QuoteAuthorModel>>>

    suspend fun getAllAuthors()
}