package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.interactors.implementations.SearchFavouriteAuthorsInteractorImpl
import com.levit.book_me.interactors.implementations.UploadProfileImageInteractorImpl
import com.levit.book_me.interactors.interfaces.SearchFavouriteAuthorsInteractor
import com.levit.book_me.interactors.interfaces.UploadProfileImageInteractor
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadStreamRepository
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadUriRepository
import com.levit.book_me.repositories.interfaces.SearchAuthorsRepository
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
}