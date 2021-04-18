package com.levit.book_me.repositories.implementations

import android.widget.ListAdapter
import com.google.android.gms.auth.api.Auth
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.core_base.extensions.flowOnIO
import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.GoogleBooksResponse
import com.levit.book_me.repositories.interfaces.SearchAuthorsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class SearchAuthorsRepositoryImpl @Inject constructor(
    @Named("IODispatcherContext")
    private val launchContext: CoroutineContext,

    private val dataSource: GoogleBooksVolumeDataSource,
): SearchAuthorsRepository {

    /**
     * Looks like sheet, but kotlin flow doesn't provide
     * ability to transform base Flow to SharedFlow, so
     * this is the best decision I invented
     */
    private val dataSourceResult = dataSource.searchResult
        .flowOnIO()
        .transformLatest<RetrofitResult<GoogleBooksResponse>, RetrofitResult<List<Author>>> { result: RetrofitResult<GoogleBooksResponse> ->
            if (result is RetrofitResult.Failure<*>) {
                emit(result)
                return@transformLatest
            }
            transformResponse(this, result)
        }
        .onEach { result ->
            _searchResult.emit(result)
        }

    private val _searchResult: MutableSharedFlow<RetrofitResult<List<Author>>> = MutableSharedFlow()
    override val searchResult: SharedFlow<RetrofitResult<List<Author>>>
        get() = _searchResult

    override suspend fun searchAuthors(queryParams: GoogleBooksVolumeParameters) {
        withContext(launchContext) {
            dataSource.searchVolumes(queryParams)
        }
    }

    private suspend fun transformResponse(collector: FlowCollector<RetrofitResult<List<Author>>>, result: RetrofitResult<GoogleBooksResponse>) {
        val response = (result as RetrofitResult.Success).data
        val authors = response.getAllAuthors()
        val newResult = RetrofitResult.Success.Value(authors)
        collector.emit(newResult)
    }

    private fun GoogleBooksResponse.getAllAuthors(): List<Author> {
        val response = responseResult ?: return emptyList()
        val authors = mutableListOf<Author>()

        response.forEach{  volume ->
            val book = volume.volumeResult
            val bookAuthors = book.listOfAuthors.map {name ->
                Author(name)
            }
            authors.addAll(bookAuthors)
        }

        return authors
    }
}