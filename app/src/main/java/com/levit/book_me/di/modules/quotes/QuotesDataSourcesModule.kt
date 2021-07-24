package com.levit.book_me.di.modules.quotes

import com.levit.book_me.data_sources.quotes.QuotesAuthorDataSource
import com.levit.book_me.data_sources.quotes.QuotesQuoteDataSource
import com.levit.book_me.data_sources.quotes.QuotesTagDataSource
import com.levit.book_me.data_sources.quotes.impl.QuotesAuthorDataSourceImpl
import com.levit.book_me.data_sources.quotes.impl.QuotesQuoteDataSourceImpl
import com.levit.book_me.data_sources.quotes.impl.QuotesTagDataSourceImpl
import com.levit.book_me.network.services.QuotesService
import dagger.Module
import dagger.Provides

@Module
class QuotesDataSourcesModule {

    @Provides
    fun provideGoQuotesTagDataSource(service: QuotesService): QuotesTagDataSource {
        return QuotesTagDataSourceImpl(service)
    }

    @Provides
    fun provideGoQuotesAuthorDataSource(service: QuotesService): QuotesAuthorDataSource {
        return QuotesAuthorDataSourceImpl(service)
    }

    @Provides
    fun provideGoQuoteQuoteDataSource(service: QuotesService): QuotesQuoteDataSource {
        return QuotesQuoteDataSourceImpl(service)
    }
}