package com.levit.book_me.repositories.implementations

import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.SearchAuthorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class SearchAuthorsRepositoryImpl @Inject constructor(
    @Named("IODispatcherContext")
    private val launchContext: CoroutineContext,
): SearchAuthorsRepository {

    private val _searchResult = MutableSharedFlow<RetrofitResult<List<Author>>>()
    override val searchResult: SharedFlow<RetrofitResult<List<Author>>> = _searchResult

    override suspend fun searchAuthors(queryParams: GoogleBooksVolumeParameters) {
        withContext(launchContext) {

        }
    }
}