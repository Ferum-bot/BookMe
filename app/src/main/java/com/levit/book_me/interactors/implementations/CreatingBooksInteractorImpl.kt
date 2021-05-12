package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.GoogleBooksVolumeParameters
import com.levit.book_me.interactors.interfaces.CreatingBooksInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.SearchBooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreatingBooksInteractorImpl @Inject constructor(
    val repository: SearchBooksRepository
): CreatingBooksInteractor {

    override val books: Flow<RetrofitResult<List<GoogleBook>>>
        get() = repository.books

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