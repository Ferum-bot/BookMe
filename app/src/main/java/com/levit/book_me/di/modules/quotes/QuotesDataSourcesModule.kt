package com.levit.book_me.di.modules.quotes

import com.levit.book_me.data_sources.implementations.GoQuotesAuthorDataSourceImpl
import com.levit.book_me.data_sources.implementations.GoQuotesQuoteDataSourceImpl
import com.levit.book_me.data_sources.implementations.GoQuotesTagDataSourceImpl
import com.levit.book_me.data_sources.interfaces.GoQuotesAuthorDataSource
import com.levit.book_me.data_sources.interfaces.GoQuotesQuoteDataSource
import com.levit.book_me.data_sources.interfaces.GoQuotesTagDataSource
import com.levit.book_me.network.services.GoQuotesService
import dagger.Module
import dagger.Provides

@Module
class QuotesDataSourcesModule {

    @Provides
    fun provideGoQuotesTagDataSource(service: GoQuotesService): GoQuotesTagDataSource {
        return GoQuotesTagDataSourceImpl(service)
    }

    @Provides
    fun provideGoQuotesAuthorDataSource(service: GoQuotesService): GoQuotesAuthorDataSource {
        return GoQuotesAuthorDataSourceImpl(service)
    }

    @Provides
    fun provideGoQuoteQuoteDataSource(service: GoQuotesService): GoQuotesQuoteDataSource {
        return GoQuotesQuoteDataSourceImpl(service)
    }
}