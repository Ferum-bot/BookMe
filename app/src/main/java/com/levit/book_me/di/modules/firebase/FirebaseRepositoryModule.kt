package com.levit.book_me.di.modules.firebase

import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadUriDataSource
import com.levit.book_me.repositories.implementations.FirebaseStorageUploadStreamRepositoryImpl
import com.levit.book_me.repositories.implementations.FirebaseStorageUploadUriRepositoryImpl
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadStreamRepository
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadUriRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
open class FirebaseRepositoryModule {

    @Provides
    fun provideStorageUploadStreamRepository(
        dataSource: FirebaseStorageUploadStreamDataSource,

        @Named("IODispatcherContext")
        coroutineContext: CoroutineContext
    ): FirebaseStorageUploadStreamRepository {
        return FirebaseStorageUploadStreamRepositoryImpl(dataSource, coroutineContext)
    }

    @Provides
    fun provideStorageUploadUriRepository(
        dataSource: FirebaseStorageUploadUriDataSource,

        @Named("IODispatcherContext")
        coroutineContext: CoroutineContext
    ): FirebaseStorageUploadUriRepository {
        return FirebaseStorageUploadUriRepositoryImpl(dataSource, coroutineContext)
    }

}