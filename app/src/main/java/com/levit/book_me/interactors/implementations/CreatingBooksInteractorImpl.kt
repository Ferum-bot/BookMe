package com.levit.book_me.interactors.implementations

import com.levit.book_me.interactors.interfaces.CreatingBooksInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreatingBooksInteractorImpl @Inject constructor(

): CreatingBooksInteractor {

    override val popularBooks: Flow<RetrofitResult<List<GoogleBook>>>
        get() = TODO("Not yet implemented")
    override val mostChosenBooks: Flow<List<RetrofitResult<GoogleBook>>>
        get() = TODO("Not yet implemented")

    override suspend fun getPopularBooks() {
        TODO("Not yet implemented")
    }

    override suspend fun getMostChosenBooks() {
        TODO("Not yet implemented")
    }
}