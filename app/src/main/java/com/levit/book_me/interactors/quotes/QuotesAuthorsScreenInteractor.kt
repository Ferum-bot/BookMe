package com.levit.book_me.interactors.quotes

import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesAuthorsScreenInteractor {

    val authors: Flow<RetrofitResult<List<QuoteAuthorModel>>>

    suspend fun getAllAuthors()
}