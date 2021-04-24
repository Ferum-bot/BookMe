package com.levit.book_me.repositories.implementations

import com.levit.book_me.core.models.GoQuotesTag
import com.levit.book_me.data_sources.interfaces.GoQuotesTagDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.GoQuotesTagsResponse
import com.levit.book_me.repositories.interfaces.GoQuotesTagRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class GoQuotesTagRepositoryImpl @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineContext: CoroutineContext,

    private val dataSource: GoQuotesTagDataSource
): GoQuotesTagRepository {

    companion object {
        private const val REPLAY_NUMBER = 1
    }

    private val scope = CoroutineScope(coroutineContext)

    private val _tags = dataSource.data
        .transformLatest<RetrofitResult<GoQuotesTagsResponse>, RetrofitResult<List<GoQuotesTag>>> { result ->
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

    override val tags: SharedFlow<RetrofitResult<List<GoQuotesTag>>>
        get() = _tags

    override suspend fun getAllTags() {
       withContext(coroutineContext) {
           dataSource.getAllTags()
       }
    }

    private suspend fun mapResponse(
        collector: FlowCollector<RetrofitResult<List<GoQuotesTag>>>,
        result: RetrofitResult.Success<GoQuotesTagsResponse>
    ) {
        val response = result.data
        val resultList = mutableListOf<GoQuotesTag>()
        response.tags?.forEach { tag ->
            resultList.add(tag)
        }
        val newResult = RetrofitResult.Success.Value(resultList)
        collector.emit(newResult)
    }
}