package com.levit.book_me.interactors.interfaces

import com.levit.book_me.core.models.Author
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface SearchFavouriteAuthorsInteractor {

    val searchAuthorsResult: Flow<RetrofitResult<List<Author>>>

    suspend fun searchAuthors(searchQuery: String)
}