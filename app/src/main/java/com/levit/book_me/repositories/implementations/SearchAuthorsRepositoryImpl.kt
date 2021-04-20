package com.levit.book_me.repositories.implementations

import android.widget.ListAdapter
import com.google.android.gms.auth.api.Auth
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.core_base.extensions.flowOnIO
import com.levit.book_me.data_sources.implementations.GoogleBooksVolumeDataSourceImpl
import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.GoogleBooksResponse
import com.levit.book_me.repositories.interfaces.SearchAuthorsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class SearchAuthorsRepositoryImpl @Inject constructor(
    @Named("IODispatcherContext")
    private val launchContext: CoroutineContext,

    private val dataSource: GoogleBooksVolumeDataSource,
): SearchAuthorsRepository {

    companion object {
        private const val REPLAY_NUMBER = 1
    }

    private val IOScope = CoroutineScope(Dispatchers.IO)

    override val searchResult: SharedFlow<RetrofitResult<List<Author>>> = dataSource.searchResult
        .flowOnIO()
        .transform<RetrofitResult<GoogleBooksResponse>, RetrofitResult<List<Author>>> { result: RetrofitResult<GoogleBooksResponse> ->
            if (result is RetrofitResult.Failure<*>) {
                emit(result)
                return@transform
            }
            transformResponse(this, result)
        }
        .shareIn(
            scope = IOScope,
            started = SharingStarted.Lazily,
            replay = REPLAY_NUMBER
        )

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
            val bookAuthors = book.listOfAuthors?.map {name ->
                Author(name)
            }
            authors.addAll(bookAuthors ?: emptyList())
        }

        return authors.distinctBy { it.fullName }
    }
}