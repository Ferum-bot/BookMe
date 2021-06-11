package com.levit.book_me.di.modules.firebase

import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadUriDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.repositories.firebase.impl.FirebaseStorageUploadStreamRepositoryImpl
import com.levit.book_me.repositories.firebase.impl.FirebaseStorageUploadUriRepositoryImpl
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadStreamRepository
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
open class FirebaseRepositoryModule {

    @Provides
    fun provideStorageUploadStreamRepository(
        dataSource: FirebaseStorageUploadStreamDataSource,

        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext
    ): FirebaseStorageUploadStreamRepository {
        return FirebaseStorageUploadStreamRepositoryImpl(dataSource, coroutineContext)
    }

    @Provides
    fun provideStorageUploadUriRepository(
        dataSource: FirebaseStorageUploadUriDataSource,

        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext
    ): FirebaseStorageUploadUriRepository {
        return FirebaseStorageUploadUriRepositoryImpl(dataSource, coroutineContext)
    }

}