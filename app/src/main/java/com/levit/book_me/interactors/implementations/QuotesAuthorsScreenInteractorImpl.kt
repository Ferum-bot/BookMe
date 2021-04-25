package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.GoQuotesAuthor
import com.levit.book_me.interactors.interfaces.QuotesAuthorsScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.GoQuotesAuthorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesAuthorsScreenInteractorImpl @Inject constructor(
    private val repository: GoQuotesAuthorRepository
): QuotesAuthorsScreenInteractor {

    override val authors: Flow<RetrofitResult<List<GoQuotesAuthor>>>
        get() = repository.authors

    override suspend fun getAllAuthors() {
        repository.getAllAuthors()
    }
}