package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.interactors.interfaces.QuotesMainScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesMainScreenInteractorImpl @Inject constructor(

): QuotesMainScreenInteractor {

    override val numberOfTags: Flow<RetrofitResult<Int>>
        get() = TODO("Not yet implemented")
    override val numberOfAuthors: Flow<RetrofitResult<Int>>
        get() = TODO("Not yet implemented")
    override val randomQuotes: Flow<RetrofitResult<List<GoQuote>>>
        get() = TODO("Not yet implemented")

    override suspend fun getNumberOfTags() {
        TODO("Not yet implemented")
    }

    override suspend fun getNumberOfAuthors() {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomQuotes() {
        TODO("Not yet implemented")
    }
}