package com.levit.book_me.di.modules

import com.levit.book_me.data_sources.implementations.GoQuotesAuthorDataSourceImpl
import com.levit.book_me.data_sources.implementations.GoQuotesQuoteDataSourceImpl
import com.levit.book_me.data_sources.implementations.GoQuotesTagDataSourceImpl
import com.levit.book_me.data_sources.implementations.GoogleBooksVolumeDataSourceImpl
import com.levit.book_me.data_sources.interfaces.GoQuotesAuthorDataSource
import com.levit.book_me.data_sources.interfaces.GoQuotesQuoteDataSource
import com.levit.book_me.data_sources.interfaces.GoQuotesTagDataSource
import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.network.services.GoQuotesService
import com.levit.book_me.network.services.GoogleBooksService
import dagger.Module
import dagger.Provides

@Module
open class DataSourceModule {

    @Provides
    fun provideGoogleBooksVolumeDataSource(service: GoogleBooksService): GoogleBooksVolumeDataSource {
        return GoogleBooksVolumeDataSourceImpl(service)
    }

    @Provides
    fun provideGoQuotesTagDataSource(service: GoQuotesService): GoQuotesTagDataSource {
        return GoQuotesTagDataSourceImpl(service)
    }

    @Provides
    fun provideGoQuotesAuthorDataSource(service: GoQuotesService): GoQuotesAuthorDataSource {
        return GoQuotesAuthorDataSourceImpl(service)
    }

    @Provides
    fun provideGoQuotesQuoteDataSource(service: GoQuotesService): GoQuotesQuoteDataSource {
        return GoQuotesQuoteDataSourceImpl(service)
    }
}