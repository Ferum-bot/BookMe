package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.enums.GoogleBooksSearchTypes
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.interfaces.SearchFavouriteAuthorsInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.SearchAuthorsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@CreatingProfileScope
class SearchFavouriteAuthorsInteractorImpl @Inject constructor(
    private val repository: SearchAuthorsRepository
): SearchFavouriteAuthorsInteractor {

    override val searchAuthorsResult: Flow<RetrofitResult<List<Author>>>
    get() = repository.searchResult

    override suspend fun searchAuthors(searchQuery: String) {
        val googleBooksParams = GoogleBooksVolumeParameters(
            textToSearch = searchQuery,
            searchType = GoogleBooksSearchTypes.SEARCH_IN_AUTHOR
        )
        repository.searchAuthors(googleBooksParams)
    }
}