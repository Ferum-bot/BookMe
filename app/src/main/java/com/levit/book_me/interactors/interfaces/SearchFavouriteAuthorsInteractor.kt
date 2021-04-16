package com.levit.book_me.interactors.interfaces

import com.levit.book_me.core.models.Author
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface SearchFavouriteAuthorsInteractor {

    val searchAuthorsResult: Flow<RetrofitResult<List<Author>>>

    suspend fun searchAuthors(searchQuery: String)
}