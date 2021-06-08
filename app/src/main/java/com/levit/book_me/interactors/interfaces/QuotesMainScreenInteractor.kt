package com.levit.book_me.interactors.interfaces

import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesMainScreenInteractor {

    val screenModel: Flow<RetrofitResult<QuotesMainScreenModel>>

    suspend fun getNumberOfTags()

    suspend fun getNumberOfAuthors()

    suspend fun getRandomQuotes()
}