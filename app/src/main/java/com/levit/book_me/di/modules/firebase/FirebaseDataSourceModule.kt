package com.levit.book_me.di.modules.firebase

import com.levit.book_me.data_sources.implementations.FirebaseStorageUploadStreamDataSourceImpl
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadStreamDataSource
import dagger.Module
import dagger.Provides

@Module
open class FirebaseDataSourceModule {

    @Provides
    fun provideStorageUploadStreamDataSource(): FirebaseStorageUploadStreamDataSource {
        return FirebaseStorageUploadStreamDataSourceImpl()
    }

}