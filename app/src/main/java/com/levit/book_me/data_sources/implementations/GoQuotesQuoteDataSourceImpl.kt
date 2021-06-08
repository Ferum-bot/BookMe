package com.levit.book_me.data_sources.implementations

import com.levit.book_me.core.exceptions.HttpException
import com.levit.book_me.core.models.GoQuotesParameters
import com.levit.book_me.data_sources.interfaces.GoQuotesQuoteDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.GoQuotesQuoteResponse
import com.levit.book_me.network.services.GoQuotesService
import com.levit.book_me.network.utill.NetworkConstants
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject


class GoQuotesQuoteDataSourceImpl @Inject constructor(
    private val service: GoQuotesService
): GoQuotesQuoteDataSource {

    companion object {
        private const val REPLAY_NUMBER = 1
        private const val CAPACITY_NUMBER = 1

        private const val RANDOM_URL = "random"
        private const val RANDOM_WITH_PARAMS_URL = "random/{count}"
        private const val ALL_WITH_FILTER_URL = "all"
    }

    private val _data: MutableSharedFlow<RetrofitResult<GoQuotesQuoteResponse>> = MutableSharedFlow(
        replay = REPLAY_NUMBER,
        extraBufferCapacity = CAPACITY_NUMBER,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    override val data: SharedFlow<RetrofitResult<GoQuotesQuoteResponse>>
        get() = _data

    override suspend fun getRandomQuotes(numberOfQuotes: Int) {
        val result = service.getRandomQuotes(numberOfQuotes)
            .parseRowResult(RANDOM_URL)
        _data.emit(result)
    }

    override suspend fun getRandomQuotesWithParams(params: GoQuotesParameters) {
        val result = service.getRandomQuotesWithFilter(
            count = params.resultCount,
            type = params.type.string,
            typeValue = params.valueToSearch
        ).parseRowResult(RANDOM_WITH_PARAMS_URL)
        _data.emit(result)
    }

    override suspend fun getAllQuotesWithParams(params: GoQuotesParameters) {
        val result = service.getAllQuotesWithFilter(
            type = params.type.string,
            typeValue = params.valueToSearch
        ).parseRowResult(ALL_WITH_FILTER_URL)
        _data.emit(result)
    }

    private fun RetrofitResult<GoQuotesQuoteResponse>.parseRowResult(url: String): RetrofitResult<GoQuotesQuoteResponse> {
        if (this !is RetrofitResult.Success) {
            return this as RetrofitResult.Failure<*>
        }
        val response = this.data
        return if (response.quotes == null || response.statusCode != 200) {
            response.parseToErrorResult(url)
        }
        else {
            this
        }
    }

    private fun GoQuotesQuoteResponse.parseToErrorResult(url: String): RetrofitResult.Failure<*> {
        val exception = HttpException(
            statusCode,
            statusMessage,
            NetworkConstants.GO_QUOTES_API_BASE_URL + url
        )
        return  RetrofitResult.Failure.HttpError(exception)
    }
}