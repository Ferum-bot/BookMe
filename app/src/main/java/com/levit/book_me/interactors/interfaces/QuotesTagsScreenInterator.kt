package com.levit.book_me.interactors.interfaces

import com.levit.book_me.core.models.quote.GoQuotesTag
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesTagsScreenInterator {

    val tags: Flow<RetrofitResult<List<GoQuotesTag>>>

    suspend fun getAllTags()
}