package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.core.models.GoQuotesAuthor
import com.levit.book_me.core.models.GoQuotesTag
import com.levit.book_me.core.models.QuotesMainScreenModel
import com.levit.book_me.interactors.interfaces.QuotesMainScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.interfaces.GoQuotesAuthorRepository
import com.levit.book_me.repositories.interfaces.GoQuotesQuoteRepository
import com.levit.book_me.repositories.interfaces.GoQuotesTagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class QuotesMainScreenInteractorImpl @Inject constructor(
    private val tagsRepository: GoQuotesTagRepository,
    private val authorsRepository: GoQuotesAuthorRepository,
    private val quoteRepository: GoQuotesQuoteRepository,
): QuotesMainScreenInteractor {

    companion object {
        private const val QUOTES_NUMBER = 40
    }

    private val _screenModel = combine<
        RetrofitResult<List<GoQuotesTag>>,
        RetrofitResult<List<GoQuotesAuthor>>,
        RetrofitResult<List<GoQuote>>,
        RetrofitResult<QuotesMainScreenModel>
    >(
        tagsRepository.tags,
        authorsRepository.authors,
        quoteRepository.quotes,
    ) { tagsResult, authorsResult, quotesResult ->

        if (tagsResult is RetrofitResult.Failure<*>) {
            return@combine tagsResult
        }
        if (authorsResult is RetrofitResult.Failure<*>) {
            return@combine authorsResult
        }
        if (quotesResult is RetrofitResult.Failure<*>) {
            return@combine quotesResult
        }

        return@combine buildModel(
            tags = (tagsResult as RetrofitResult.Success).data,
            authors = (authorsResult as RetrofitResult.Success).data,
            quotes = (quotesResult as RetrofitResult.Success).data
        )
    }

    override val screenModel: Flow<RetrofitResult<QuotesMainScreenModel>>
        get() = _screenModel

    override suspend fun getNumberOfTags() {
        tagsRepository.getAllTags()
    }

    override suspend fun getNumberOfAuthors() {
        authorsRepository.getAllAuthors()
    }

    override suspend fun getRandomQuotes() {
        quoteRepository.getRandomQuotes(QUOTES_NUMBER)
    }

    private fun buildModel(
        tags: List<GoQuotesTag>,
        authors: List<GoQuotesAuthor>,
        quotes: List<GoQuote>
    ): RetrofitResult<QuotesMainScreenModel> {
        val numberOfTags = tags.size
        val numberOfAuthors = authors.size
        val model = QuotesMainScreenModel(
            numberOfTags,
            numberOfAuthors,
            quotes
        )
        return RetrofitResult.Success.Value(model)
    }
}