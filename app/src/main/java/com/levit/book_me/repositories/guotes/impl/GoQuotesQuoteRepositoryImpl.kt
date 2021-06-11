package com.levit.book_me.repositories.guotes.impl

import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core.models.GoQuotesParameters
import com.levit.book_me.data_sources.quotes.GoQuotesQuoteDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.GoQuotesQuoteResponse
import com.levit.book_me.repositories.guotes.GoQuotesQuoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class GoQuotesQuoteRepositoryImpl @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineContext: CoroutineContext,

    private val dataSource: GoQuotesQuoteDataSource
): GoQuotesQuoteRepository {

    companion object {
        private const val REPLAY_NUMBER = 1
    }

    private val scope = CoroutineScope(coroutineContext)

    private val _quotes = dataSource.data
        .transformLatest<RetrofitResult<GoQuotesQuoteResponse>, RetrofitResult<List<GoQuote>>> { result ->
            if (result is RetrofitResult.Failure<*>) {
                emit(result)
                return@transformLatest
            }
            mapResponse(this, result as RetrofitResult.Success)
        }
        .flowOn(Dispatchers.Default)
        .shareIn(
            scope = scope,
            started = SharingStarted.Lazily,
            replay = REPLAY_NUMBER
        )

    override val quotes: SharedFlow<RetrofitResult<List<GoQuote>>>
        get() = _quotes

    override suspend fun getRandomQuotes(count: Int) {
        withContext(coroutineContext) {
            dataSource.getRandomQuotes(count)
        }
    }

    override suspend fun getRandomQuotesWithParams(params: GoQuotesParameters) {
        withContext(coroutineContext) {
            dataSource.getRandomQuotesWithParams(params)
        }
    }

    override suspend fun getAllQuotesWithParams(params: GoQuotesParameters) {
        withContext(coroutineContext) {
            dataSource.getAllQuotesWithParams(params)
        }
    }

    private suspend fun mapResponse(
        collector: FlowCollector<RetrofitResult<List<GoQuote>>>,
        result: RetrofitResult.Success<GoQuotesQuoteResponse>,
    ) {
        val response = result.data
        val quotes = mutableListOf<GoQuote>()
        response.quotes?.forEach { quote ->
            quotes.add(quote)
        }
        val newResult = RetrofitResult.Success.Value(quotes)
        collector.emit(newResult)
    }
}