package com.levit.book_me.interactors.quotes.impl

import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core.models.quote.GoQuotesAuthor
import com.levit.book_me.core.models.quote.GoQuotesTag
import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.interactors.quotes.QuotesMainScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.guotes.GoQuotesAuthorRepository
import com.levit.book_me.repositories.guotes.GoQuotesQuoteRepository
import com.levit.book_me.repositories.guotes.GoQuotesTagRepository
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