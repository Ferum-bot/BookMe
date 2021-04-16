package com.levit.book_me.repositories.interfaces

import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface SearchAuthorsRepository {

    val searchResult: SharedFlow<RetrofitResult<List<Author>>>

    suspend fun searchAuthors(queryParams: GoogleBooksVolumeParameters)

}