package com.levit.book_me.data_sources.quotes

import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface QuotesQuoteDataSource {

    val mainScreenModel: SharedFlow<RetrofitResult<QuotesMainScreenModel>>

    val quotes: SharedFlow<RetrofitResult<List<QuoteModel>>>

    suspend fun getMainScreenModel(quotesCount: Int)

    suspend fun getAllQuotesByAuthor(author: String)

    suspend fun getAllQuotesByTag(tag: String)

}