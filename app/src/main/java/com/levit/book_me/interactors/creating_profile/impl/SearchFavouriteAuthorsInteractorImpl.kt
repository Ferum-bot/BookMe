package com.levit.book_me.interactors.creating_profile.impl

import com.levit.book_me.core.enums.GoogleBooksSearchTypes
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.google_books.GoogleBooksVolumeParameters
import com.levit.book_me.core_base.di.SearchFavouriteAuthorsScope
import com.levit.book_me.interactors.creating_profile.SearchFavouriteAuthorsInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.google.SearchAuthorsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@SearchFavouriteAuthorsScope
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