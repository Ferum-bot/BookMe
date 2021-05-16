package com.levit.book_me.repositories.implementations

import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.models.google_books.GoogleBookVolume
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.google_books.GoogleBooksResponse
import com.levit.book_me.repositories.interfaces.SearchBooksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class SearchBooksRepositoryImpl @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val launchContext: CoroutineContext,

    private val dataSource: GoogleBooksVolumeDataSource,
): SearchBooksRepository {

    companion object {
        private const val REPLAY_NUMBER = 1
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    override val books: SharedFlow<RetrofitResult<List<GoogleBook>>> = dataSource.searchResult
        .transform<RetrofitResult<GoogleBooksResponse>, RetrofitResult<List<GoogleBook>>> { result: RetrofitResult<GoogleBooksResponse> ->
            if (result is RetrofitResult.Failure<*>) {
                emit(result)
                return@transform
            }
            transformResponse(result as RetrofitResult.Success)
        }
        .flowOn(Dispatchers.Default)
        .shareIn(
            scope = scope,
            started = SharingStarted.Lazily,
            replay = REPLAY_NUMBER
        )

    override suspend fun searchBooks(queryParams: GoogleBooksVolumeParameters) =
    withContext(launchContext) {
        dataSource.searchVolumes(queryParams)
    }

    override suspend fun searchPopularBooks(queryParams: GoogleBooksVolumeParameters) =
    withContext(launchContext) {
        dataSource.searchPopularVolumes(queryParams)
    }

    override suspend fun searchMostChosenBooks(queryParams: GoogleBooksVolumeParameters) =
    withContext(launchContext) {
        dataSource.searchMostChosenVolumes(queryParams)
    }

    private suspend fun FlowCollector<RetrofitResult<List<GoogleBook>>>.transformResponse(
        result: RetrofitResult.Success<GoogleBooksResponse>
    ) {
        val response = result.data
        val resultList = mutableListOf<GoogleBook>()

        if (response.responseResult == null) {
            val newResult = RetrofitResult.Success.Value(resultList)
            emit(newResult)
            return
        }

        resultList.addAll(response.responseResult.getAllBooks())
        val newResult = RetrofitResult.Success.Value(resultList)
        emit(newResult)
    }

    private fun List<GoogleBookVolume>.getAllBooks(): List<GoogleBook> {
        val result = mutableListOf<GoogleBook>()
        forEach { volume ->
            result.add(volume.volumeResult)
        }
        return result
    }
}