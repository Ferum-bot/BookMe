package com.levit.book_me.data_sources.quotes.impl

import com.levit.book_me.core.exceptions.HttpException
import com.levit.book_me.data_sources.quotes.GoQuotesTagDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.GoQuotesTagsResponse
import com.levit.book_me.network.services.GoQuotesService
import com.levit.book_me.network.utill.NetworkConstants
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GoQuotesTagDataSourceImpl @Inject constructor(
    private val service: GoQuotesService
): GoQuotesTagDataSource {

    companion object {
        private const val REPLAY_NUMBER = 1
        private const val CAPACITY_NUMBER = 1
    }

    private val _data: MutableSharedFlow<RetrofitResult<GoQuotesTagsResponse>> = MutableSharedFlow(
        replay = REPLAY_NUMBER,
        extraBufferCapacity = CAPACITY_NUMBER,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val data: SharedFlow<RetrofitResult<GoQuotesTagsResponse>>
        get() = _data

    override suspend fun getAllTags() {
        val result = service.getAllQuotesTags()
            .parseRowResult()
        _data.emit(result)
    }

    private fun RetrofitResult<GoQuotesTagsResponse>.parseRowResult(): RetrofitResult<GoQuotesTagsResponse> {
        if (this !is RetrofitResult.Success) {
            return this as RetrofitResult.Failure<*>
        }
        val response = this.data
        return if (response.tags == null || response.statusCode != 200) {
            response.parseToErrorResult()
        }
        else {
            this
        }
    }

    private fun GoQuotesTagsResponse.parseToErrorResult(): RetrofitResult.Failure<*> {
        val exception = HttpException(
            statusCode,
            statusMessage,
            NetworkConstants.GO_QUOTES_API_BASE_URL + "all/tags"
        )

        return RetrofitResult.Failure.HttpError(exception)
    }
}