package com.levit.book_me.interactors.interfaces

import com.levit.book_me.core.models.quote.GoQuotesAuthor
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesAuthorsScreenInteractor {

    val authors: Flow<RetrofitResult<List<GoQuotesAuthor>>>

    suspend fun getAllAuthors()
}