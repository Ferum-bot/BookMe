package com.levit.book_me.di.modules

import com.levit.book_me.data_sources.implementations.GoogleBooksVolumeDataSourceImpl
import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.network.services.GoogleBooksService
import dagger.Module
import dagger.Provides

@Module
open class DataSourceModule {

    @Provides
    fun provideGoogleBooksVolumeDataSource(service: GoogleBooksService): GoogleBooksVolumeDataSource {
        return GoogleBooksVolumeDataSourceImpl(service)
    }
}