package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.interactors.creating_profile.impl.SearchBooksInteractorImpl
import com.levit.book_me.interactors.creating_profile.SearchBooksInteractor
import com.levit.book_me.repositories.google.SearchBooksRepository
import dagger.Module
import dagger.Provides

@Module
open class SearchBooksInteractorModule {

    @Provides
    fun provideSearchBooksInterator(repository: SearchBooksRepository): SearchBooksInteractor {
        return SearchBooksInteractorImpl(repository)
    }
}