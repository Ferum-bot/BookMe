package com.levit.book_me.core.adapters

import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.QuotesErrorResponse
import com.levit.book_me.network.response_models.go_quotes.QuotesResponse
import kotlinx.coroutines.flow.FlowCollector

object QuotesResponseAdapter {

    suspend fun <T: Any> adaptQuotesResponseAndEmit(
        rawResponse: RetrofitResult<QuotesResponse<T>>, collector: FlowCollector<RetrofitResult<T>>
    ) {

        if (rawResponse !is RetrofitResult.Success) {
            collector.emit(rawResponse as RetrofitResult.Failure<*>)
            return
        }
        val response = rawResponse.data

        if (response.data != null) {
            val adaptedResult = adaptData(response.data)
            collector.emit(adaptedResult)
            return
        }

        if (response.error != null) {
            val adaptedResult = adaptError(response.error)
            collector.emit(adaptedResult)
            return
        }
    }

    private fun <T: Any> adaptData(data: T): RetrofitResult.Success<T> {
        return RetrofitResult.Success.Value(data)
    }

    private fun adaptError(error: QuotesErrorResponse): RetrofitResult.Failure<*> {
        val message = error.message
        val throwable = Exception(message)
        return RetrofitResult.Failure.Error(throwable, message)
    }

}