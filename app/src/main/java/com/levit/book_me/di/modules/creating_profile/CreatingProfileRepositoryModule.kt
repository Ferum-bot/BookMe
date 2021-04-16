package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.repositories.implementations.SearchAuthorsRepositoryImpl
import com.levit.book_me.repositories.interfaces.SearchAuthorsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
class CreatingProfileRepositoryModule {

    @Provides
    fun provideSearchAuthorsRepository(
        @Named("IODispatcherContext")
        coroutineContext: CoroutineContext
    ): SearchAuthorsRepository {
        return SearchAuthorsRepositoryImpl(coroutineContext)
    }
}