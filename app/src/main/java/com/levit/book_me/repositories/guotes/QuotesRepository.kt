package com.levit.book_me.repositories.guotes

import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.fragments.quotes.recycler.QuotesTagAdapter
import kotlinx.coroutines.flow.SharedFlow

interface QuotesRepository {

    val mainScreenModel: SharedFlow<RetrofitResult<QuotesMainScreenModel>>

    val quotes: SharedFlow<RetrofitResult<List<QuoteModel>>>

    val tags: SharedFlow<RetrofitResult<List<QuoteTagModel>>>

    val authors: SharedFlow<RetrofitResult<List<QuoteAuthorModel>>>

    suspend fun getMainScreenModel(quotesCount: Int)

    suspend fun getAllTags()

    suspend fun getAllAuthors()

    suspend fun getAllQuotesFromAuthor(author: String)

    suspend fun getAllQuotesFromTag(tag: String)
}