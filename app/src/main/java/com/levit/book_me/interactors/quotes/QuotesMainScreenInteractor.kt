package com.levit.book_me.interactors.quotes

import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.Flow

interface QuotesMainScreenInteractor {

    val screenModel: Flow<RetrofitResult<QuotesMainScreenModel>>

    suspend fun getMainScreenModel()
}