package com.levit.book_me.repositories.guotes

import com.levit.book_me.core.models.quote.GoQuotesAuthor
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface GoQuotesAuthorRepository {

    val authors: SharedFlow<RetrofitResult<List<GoQuotesAuthor>>>

    suspend fun getAllAuthors()
}