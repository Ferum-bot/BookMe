package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.interactors.interfaces.SearchBooksInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.SearchBooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchBooksInteractorImpl @Inject constructor(
    val repository: SearchBooksRepository
): SearchBooksInteractor {

    override val books: Flow<RetrofitResult<List<GoogleBook>>>
        get() = repository.books

    override suspend fun searchBooks(query: String) {
        val params = GoogleBooksVolumeParameters(
            textToSearch = query,
        )
        repository.searchBooks(params)
    }

    override suspend fun getPopularBooks() {
        val params = GoogleBooksVolumeParameters(
            isSearchParams = false
        )
        repository.searchPopularBooks(params)
    }

    override suspend fun getMostChosenBooks() {
        val params = GoogleBooksVolumeParameters(
            isSearchParams = false
        )
        repository.searchMostChosenBooks(params)
    }
}