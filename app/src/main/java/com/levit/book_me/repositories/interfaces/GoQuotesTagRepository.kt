package com.levit.book_me.repositories.interfaces

import com.levit.book_me.core.models.quote.GoQuotesTag
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface GoQuotesTagRepository {

    val tags: SharedFlow<RetrofitResult<List<GoQuotesTag>>>

    suspend fun getAllTags()
}