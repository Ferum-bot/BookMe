package com.levit.book_me.data_sources.quotes.impl

import com.levit.book_me.core.adapters.QuotesResponseAdapter
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.data_sources.quotes.QuotesQuoteDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.services.QuotesService
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class QuotesQuoteDataSourceImpl @Inject constructor(
    private val service: QuotesService
): QuotesQuoteDataSource {

    companion object {
        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 1
    }

    private val _mainScreenModel = MutableSharedFlow<RetrofitResult<QuotesMainScreenModel>>(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val mainScreenModel: SharedFlow<RetrofitResult<QuotesMainScreenModel>>
    get() = _mainScreenModel

    private val _quotes = MutableSharedFlow<RetrofitResult<List<QuoteModel>>>(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val quotes: SharedFlow<RetrofitResult<List<QuoteModel>>>
    get() = _quotes


    override suspend fun getMainScreenModel(quotesCount: Int) {
        val rawResponse = service.getMainScreenModel(quotesCount)
        QuotesResponseAdapter.adaptQuotesResponseAndEmit(
            rawResponse, _mainScreenModel
        )
    }

    override suspend fun getAllQuotesByAuthor(author: String) {
        val rawResponse = service.getAllQuotesByAuthor(author)
        QuotesResponseAdapter.adaptQuotesResponseAndEmit(
            rawResponse, _quotes
        )
    }

    override suspend fun getAllQuotesByTag(tag: String) {
        val rawResponse = service.getAllQuotesByTag(tag)
        QuotesResponseAdapter.adaptQuotesResponseAndEmit(
            rawResponse, _quotes
        )
    }

}