package com.levit.book_me.di.modules.firebase

import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadUriDataSource
import com.levit.book_me.repositories.implementations.FirebaseStorageUploadStreamRepositoryImpl
import com.levit.book_me.repositories.implementations.FirebaseStorageUploadUriRepositoryImpl
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadStreamRepository
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadUriRepository
import dagger.Module
import dagger.Provides

@Module
open class FirebaseRepositoryModule {

    @Provides
    fun provideStorageUploadStreamRepository(
        dataSource: FirebaseStorageUploadStreamDataSource
    ): FirebaseStorageUploadStreamRepository {
        return FirebaseStorageUploadStreamRepositoryImpl(dataSource)
    }

    @Provides
    fun provideStorageUploadUriRepository(
        dataSource: FirebaseStorageUploadUriDataSource
    ): FirebaseStorageUploadUriRepository {
        return FirebaseStorageUploadUriRepositoryImpl(dataSource)
    }

}