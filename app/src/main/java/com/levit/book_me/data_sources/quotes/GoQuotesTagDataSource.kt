package com.levit.book_me.data_sources.quotes

import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.GoQuotesTagsResponse
import kotlinx.coroutines.flow.SharedFlow

interface GoQuotesTagDataSource {

    val data: SharedFlow<RetrofitResult<GoQuotesTagsResponse>>

    suspend fun getAllTags()
}