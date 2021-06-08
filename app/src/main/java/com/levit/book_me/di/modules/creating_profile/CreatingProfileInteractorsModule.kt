package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.interactors.implementations.CreatingFavouriteGenresInteractorImpl
import com.levit.book_me.interactors.implementations.SearchBooksInteractorImpl
import com.levit.book_me.interactors.implementations.UploadProfileImageInteractorImpl
import com.levit.book_me.interactors.interfaces.CreatingFavouriteGenresInteractor
import com.levit.book_me.interactors.interfaces.SearchBooksInteractor
import com.levit.book_me.interactors.interfaces.UploadProfileImageInteractor
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadUriRepository
import com.levit.book_me.repositories.interfaces.SearchBooksRepository
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
    fun provideCreatingGenresInteractor(): CreatingFavouriteGenresInteractor {
        return CreatingFavouriteGenresInteractorImpl()
    }
}