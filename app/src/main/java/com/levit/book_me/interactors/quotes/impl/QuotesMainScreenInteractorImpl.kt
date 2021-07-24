package com.levit.book_me.interactors.quotes.impl

import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.interactors.quotes.QuotesMainScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.guotes.QuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class QuotesMainScreenInteractorImpl @Inject constructor(
    private val repository: QuotesRepository,
): QuotesMainScreenInteractor {

    companion object {
        private const val QUOTES_COUNT = 40
    }

    override val screenModel: Flow<RetrofitResult<QuotesMainScreenModel>>
    get() = repository.mainScreenModel

    override suspend fun getMainScreenModel() {
        repository.getMainScreenModel(QUOTES_COUNT)
    }
}