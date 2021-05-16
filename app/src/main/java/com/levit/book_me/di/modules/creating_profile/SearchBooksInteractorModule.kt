package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.interactors.implementations.SearchBooksInteractorImpl
import com.levit.book_me.interactors.interfaces.SearchBooksInteractor
import com.levit.book_me.repositories.interfaces.SearchBooksRepository
import dagger.Module
import dagger.Provides

@Module
open class SearchBooksInteractorModule {

    @Provides
    fun provideSearchBooksInterator(repository: SearchBooksRepository): SearchBooksInteractor {
        return SearchBooksInteractorImpl(repository)
    }
}