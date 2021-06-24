package com.levit.book_me.di.modules.firebase

import com.levit.book_me.data_sources.firebase.impl.FirebaseStorageUploadStreamDataSourceImpl
import com.levit.book_me.data_sources.firebase.impl.FirebaseStorageUploadUriDataSourceImpl
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadUriDataSource
import com.levit.book_me.data_sources.profile.impl.BaseProfileRemoteDataSource
import com.levit.book_me.data_sources.profile.impl.FirestoreProfileRemoteDataSource
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

    @Provides
    fun provideFirebaseBaseDataSource(): BaseProfileRemoteDataSource {
        return FirestoreProfileRemoteDataSource()
    }
}