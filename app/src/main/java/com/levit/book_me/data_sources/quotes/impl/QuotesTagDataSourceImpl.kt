package com.levit.book_me.data_sources.quotes.impl

import com.levit.book_me.core.adapters.QuotesResponseAdapter
import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.data_sources.quotes.QuotesTagDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.services.QuotesService
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class QuotesTagDataSourceImpl @Inject constructor(
    private val service: QuotesService
): QuotesTagDataSource {

    companion object {
        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY_COUNT = 1
    }

    private val _tags = MutableSharedFlow<RetrofitResult<List<QuoteTagModel>>>(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY_COUNT,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val tags: SharedFlow<RetrofitResult<List<QuoteTagModel>>>
    get() = _tags

    override suspend fun getAllTags() {
        val rawResponse = service.getAllTags()
        QuotesResponseAdapter.adaptQuotesResponseAndEmit(
            rawResponse, _tags
        )
    }
}