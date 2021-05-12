package com.levit.book_me.di.modules

import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.repositories.implementations.SearchAuthorsRepositoryImpl
import com.levit.book_me.repositories.implementations.SearchBooksRepositoryImpl
import com.levit.book_me.repositories.interfaces.SearchAuthorsRepository
import com.levit.book_me.repositories.interfaces.SearchBooksRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
open class RepositoryModule {

    @Provides
    fun provideSearchAuthorsRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        dataSource: GoogleBooksVolumeDataSource
    ): SearchAuthorsRepository {
        return SearchAuthorsRepositoryImpl(coroutineContext, dataSource)
    }

    @Provides
    fun provideSearchBooksRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        dataSource: GoogleBooksVolumeDataSource
    ): SearchBooksRepository {
        return SearchBooksRepositoryImpl(coroutineContext, dataSource)
    }
}