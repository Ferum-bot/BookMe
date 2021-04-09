package com.levit.book_me.di.modules.creating_profile

import com.levit.book_me.interactors.implementations.UploadProfileImageInteractorImpl
import com.levit.book_me.interactors.interfaces.UploadProfileImageInteractor
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadStreamRepository
import dagger.Module
import dagger.Provides

@Module
class CreatingProfileInteractorsModule {

    @Provides
    fun provideUploadProfileImageInteractor(
        repository: FirebaseStorageUploadStreamRepository
    ): UploadProfileImageInteractor {
        return UploadProfileImageInteractorImpl(repository)
    }

}