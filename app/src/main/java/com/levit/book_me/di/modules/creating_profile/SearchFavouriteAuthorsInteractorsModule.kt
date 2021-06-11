package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.interactors.creating_profile.impl.SearchFavouriteAuthorsInteractorImpl
import com.levit.book_me.interactors.creating_profile.SearchFavouriteAuthorsInteractor
import com.levit.book_me.repositories.google.SearchAuthorsRepository
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