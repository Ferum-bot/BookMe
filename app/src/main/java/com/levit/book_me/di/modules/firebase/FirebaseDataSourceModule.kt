package com.levit.book_me.di.modules.firebase

import com.levit.book_me.data_sources.firebase.impl.FirebaseStorageUploadStreamDataSourceImpl
import com.levit.book_me.data_sources.firebase.impl.FirebaseStorageUploadUriDataSourceImpl
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadUriDataSource
import dagger.Module
import dagger.Provides

@Module
open class FirebaseDataSourceModule {

    @Provides
    fun provideStorageUploadStreamDataSource(): FirebaseStorageUploadStreamDataSource {
        return FirebaseStorageUploadStreamDataSourceImpl()
    }

    @Provides
    fun provideStorageUploadUriDataSource(): FirebaseStorageUploadUriDataSource {
        return FirebaseStorageUploadUriDataSourceImpl()
    }
}