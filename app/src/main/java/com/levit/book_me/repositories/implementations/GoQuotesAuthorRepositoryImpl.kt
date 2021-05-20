package com.levit.book_me.repositories.implementations

import com.levit.book_me.core.models.quote.GoQuotesAuthor
import com.levit.book_me.data_sources.interfaces.GoQuotesAuthorDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.GoQuotesAuthorsResponse
import com.levit.book_me.repositories.interfaces.GoQuotesAuthorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class GoQuotesAuthorRepositoryImpl @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineContext: CoroutineContext,

    private val dataSource: GoQuotesAuthorDataSource
): GoQuotesAuthorRepository {

    companion object {
        private const val REPLAY_NUMBER = 1
    }

    private val scope = CoroutineScope(coroutineContext)

    private val _authors = dataSource.data
        .transformLatest<RetrofitResult<GoQuotesAuthorsResponse>, RetrofitResult<List<GoQuotesAuthor>>> { result ->
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

    override val authors: SharedFlow<RetrofitResult<List<GoQuotesAuthor>>>
        get() = _authors

    override suspend fun getAllAuthors() {
        withContext(coroutineContext) {
            dataSource.getAllAuthors()
        }
    }

    private suspend fun mapResponse(
        collector: FlowCollector<RetrofitResult<List<GoQuotesAuthor>>>,
        result: RetrofitResult.Success<GoQuotesAuthorsResponse>
    ) {
        val response = result.data
        val authors = mutableListOf<GoQuotesAuthor>()
        response.authors?.forEach { author ->
            authors.add(author)
        }
        val newResult = RetrofitResult.Success.Value(authors)
        collector.emit(newResult)
    }
}