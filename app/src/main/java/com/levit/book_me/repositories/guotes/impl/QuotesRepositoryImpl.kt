package com.levit.book_me.repositories.guotes.impl

import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.data_sources.quotes.QuotesAuthorDataSource
import com.levit.book_me.data_sources.quotes.QuotesQuoteDataSource
import com.levit.book_me.data_sources.quotes.QuotesTagDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.guotes.QuotesRepository
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class QuotesRepositoryImpl @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineContext: CoroutineContext,

    private val quotesDataSource: QuotesQuoteDataSource,
    private val tagsDataSource: QuotesTagDataSource,
    private val authorsDataSource: QuotesAuthorDataSource,
): QuotesRepository {

    override val mainScreenModel: SharedFlow<RetrofitResult<QuotesMainScreenModel>>
    get() = quotesDataSource.mainScreenModel

    override val authors: SharedFlow<RetrofitResult<List<QuoteAuthorModel>>>
    get() = authorsDataSource.authors

    override val quotes: SharedFlow<RetrofitResult<List<QuoteModel>>>
    get() = quotesDataSource.quotes

    override val tags: SharedFlow<RetrofitResult<List<QuoteTagModel>>>
    get() = tagsDataSource.tags

    override suspend fun getMainScreenModel(quotesCount: Int) {
        withContext(coroutineContext) {
            quotesDataSource.getMainScreenModel(quotesCount)
        }
    }

    override suspend fun getAllTags() {
        withContext(coroutineContext) {
            tagsDataSource.getAllTags()
        }
    }

    override suspend fun getAllAuthors() {
        withContext(coroutineContext) {
            authorsDataSource.getAllAuthors()
        }
    }

    override suspend fun getAllQuotesFromTag(tag: String) {
        withContext(coroutineContext) {
            quotesDataSource.getAllQuotesByTag(tag)
        }
    }

    override suspend fun getAllQuotesFromAuthor(author: String) {
        withContext(coroutineContext) {
            quotesDataSource.getAllQuotesByAuthor(author)
        }
    }
}