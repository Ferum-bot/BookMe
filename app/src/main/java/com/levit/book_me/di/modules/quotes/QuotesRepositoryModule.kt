package com.levit.book_me.di.modules.quotes

import com.levit.book_me.data_sources.quotes.QuotesAuthorDataSource
import com.levit.book_me.data_sources.quotes.QuotesQuoteDataSource
import com.levit.book_me.data_sources.quotes.QuotesTagDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.repositories.guotes.QuotesRepository
import com.levit.book_me.repositories.guotes.impl.QuotesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
class QuotesRepositoryModule {

    @Provides
    fun provideQuotesRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        tagsDataSource: QuotesTagDataSource,
        authorDataSource: QuotesAuthorDataSource,
        quotesQuoteDataSource: QuotesQuoteDataSource
    ): QuotesRepository {
        return QuotesRepositoryImpl(
            coroutineContext, quotesQuoteDataSource, tagsDataSource, authorDataSource
        )
    }

}