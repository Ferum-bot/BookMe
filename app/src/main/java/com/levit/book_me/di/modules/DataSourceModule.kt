package com.levit.book_me.di.modules

import android.content.SharedPreferences
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadUriDataSource
import com.levit.book_me.data_sources.google.impl.GoogleBooksVolumeDataSourceImpl
import com.levit.book_me.data_sources.google.GoogleBooksVolumeDataSource
import com.levit.book_me.data_sources.profile.*
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.services.GoogleBooksService
import com.levit.book_me.data_sources.profile.impl.*
import com.levit.book_me.di.modules.network.CoroutineContextModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(includes = [
    CoroutineContextModule::class,
])
open class DataSourceModule {

    @Provides
    fun provideGoogleBooksVolumeDataSource(service: GoogleBooksService): GoogleBooksVolumeDataSource {
        return GoogleBooksVolumeDataSourceImpl(service)
    }

    @Provides
    fun provideCacheProfileDataSource(
        @Named(DIConstants.PROFILE_SHARED_PREFERENCE_NAME)
        preferences: SharedPreferences,
    ): CacheProfileDataSource {
        return SharedPrefProfileDataSource(preferences)
    }

    @Provides
    fun provideRegisterNewUserDataSource(): RegisterNewUserDataSource {
        return FirestoreRegisterNewUserDataSource(
            remote = Firebase.firestore,
            auth = Firebase.auth,
        )
    }

    @Provides
    fun provideRemoteProfileDataSourceFacade(
        baseDataSource: BaseProfileRemoteDataSource,
        uploadPhotoDataSource: FirebaseStorageUploadUriDataSource,
    ): ProfileRemoteDataSourceFacade {
        return FirestoreDataSourceFacade(baseDataSource, uploadPhotoDataSource)
    }

    @Provides
    fun provideLocalAuthDataSource(
        @Named(DIConstants.AUTH_INFO_SHARED_PREFERENCE_NAME)
        preferences: SharedPreferences
    ): LocalAuthDataSource {
        return SharedPrefLocalAuthDataSource(preferences)
    }

    @Provides
    fun provideRemoteAuthDataSource(

    ): RemoteAuthDataSource {
        return RemoteAuthDataSourceImpl()
    }
}