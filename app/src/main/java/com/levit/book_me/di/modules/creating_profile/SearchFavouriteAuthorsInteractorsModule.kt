package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.interactors.implementations.SearchFavouriteAuthorsInteractorImpl
import com.levit.book_me.interactors.interfaces.SearchFavouriteAuthorsInteractor
import com.levit.book_me.repositories.interfaces.SearchAuthorsRepository
import dagger.Module
import dagger.Provides

@Module
class SearchFavouriteAuthorsInteractorsModule {

    @Provides
    fun provideSearchFavouriteAuthorsInteractor(
            repository: SearchAuthorsRepository
    ): SearchFavouriteAuthorsInteractor {
        return SearchFavouriteAuthorsInteractorImpl(repository)
    }

}