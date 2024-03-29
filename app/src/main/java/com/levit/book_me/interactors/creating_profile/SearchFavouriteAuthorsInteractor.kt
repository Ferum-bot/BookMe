package com.levit.book_me.interactors.creating_profile

import com.levit.book_me.core.models.Author
import com.levit.book_me.core_base.di.SearchFavouriteAuthorsScope
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface SearchFavouriteAuthorsInteractor {

    val searchAuthorsResult: Flow<RetrofitResult<List<Author>>>

    suspend fun searchAuthors(searchQuery: String)
}