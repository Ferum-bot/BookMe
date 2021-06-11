package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.interactors.creating_profile.impl.CreatingFavouriteGenresInteractorImpl
import com.levit.book_me.interactors.creating_profile.impl.SearchBooksInteractorImpl
import com.levit.book_me.interactors.creating_profile.impl.UploadProfileImageInteractorImpl
import com.levit.book_me.interactors.creating_profile.CreatingFavouriteGenresInteractor
import com.levit.book_me.interactors.creating_profile.CreatingProfileInteractor
import com.levit.book_me.interactors.creating_profile.SearchBooksInteractor
import com.levit.book_me.interactors.creating_profile.UploadProfileImageInteractor
import com.levit.book_me.interactors.creating_profile.impl.CacheCreatingProfileInteractor
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import com.levit.book_me.repositories.profile.GenresRepository
import com.levit.book_me.repositories.google.SearchBooksRepository
import dagger.Module
import dagger.Provides

@Module
class CreatingProfileInteractorsModule {

    @Provides
    fun provideUploadProfileImageInteractor(
        repository: FirebaseStorageUploadUriRepository
    ): UploadProfileImageInteractor {
        return UploadProfileImageInteractorImpl(repository)
    }

    @Provides
    fun provideCreatingBooksInteractor(
        repository: SearchBooksRepository
    ): SearchBooksInteractor {
        return SearchBooksInteractorImpl(repository)
    }

    @Provides
    fun provideCreatingGenresInteractor(
        repository: GenresRepository
    ): CreatingFavouriteGenresInteractor {
        return CreatingFavouriteGenresInteractorImpl(repository)
    }

    @Provides
    fun provideActivityCreatingProfileInteractor(
        cacheDataSource: CacheProfileDataSource
    ): CreatingProfileInteractor {
        return CacheCreatingProfileInteractor(cacheDataSource)
    }
}