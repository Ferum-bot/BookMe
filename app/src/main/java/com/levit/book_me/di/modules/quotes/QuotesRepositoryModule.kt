package com.levit.book_me.di.modules.quotes

import com.levit.book_me.data_sources.quotes.GoQuotesAuthorDataSource
import com.levit.book_me.data_sources.quotes.GoQuotesQuoteDataSource
import com.levit.book_me.data_sources.quotes.GoQuotesTagDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.repositories.guotes.impl.GoQuotesAuthorRepositoryImpl
import com.levit.book_me.repositories.guotes.impl.GoQuotesQuoteRepositoryImpl
import com.levit.book_me.repositories.guotes.impl.GoQuotesTagRepositoryImpl
import com.levit.book_me.repositories.guotes.GoQuotesAuthorRepository
import com.levit.book_me.repositories.guotes.GoQuotesQuoteRepository
import com.levit.book_me.repositories.guotes.GoQuotesTagRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
class QuotesRepositoryModule {

    @Provides
    fun provideGoQuoteTagRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        dataSource: GoQuotesTagDataSource
    ): GoQuotesTagRepository {
        return GoQuotesTagRepositoryImpl(coroutineContext, dataSource)
    }

    @Provides
    fun provideGoQuoteAuthorRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        dataSource: GoQuotesAuthorDataSource
    ): GoQuotesAuthorRepository {
        return GoQuotesAuthorRepositoryImpl(coroutineContext, dataSource)
    }

    @Provides
    fun provideGoQuotesQuoteRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        dataSource: GoQuotesQuoteDataSource
    ): GoQuotesQuoteRepository {
        return GoQuotesQuoteRepositoryImpl(coroutineContext, dataSource)
    }
}