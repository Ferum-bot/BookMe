package com.levit.book_me.data_sources.quotes.impl

import com.levit.book_me.core.adapters.QuotesResponseAdapter
import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.data_sources.quotes.QuotesAuthorDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.services.QuotesService
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class QuotesAuthorDataSourceImpl @Inject constructor(
    private val service: QuotesService,
): QuotesAuthorDataSource {

    companion object {
        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 1
    }

    private val _authors = MutableSharedFlow<RetrofitResult<List<QuoteAuthorModel>>>(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val authors: SharedFlow<RetrofitResult<List<QuoteAuthorModel>>>
    get() = _authors

    override suspend fun getAllAuthors() {
        val rawResponse = service.getAllAuthors()

        QuotesResponseAdapter.adaptQuotesResponseAndEmit(
            rawResponse, _authors
        )
    }
}