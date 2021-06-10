package com.levit.book_me.di.modules

import android.content.SharedPreferences
import com.levit.book_me.data_sources.implementations.GoogleBooksVolumeDataSourceImpl
import com.levit.book_me.data_sources.interfaces.GoogleBooksVolumeDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.services.GoogleBooksService
import com.levit.book_me.repositories.implementations.SharedPrefProfileDataSource
import com.levit.book_me.repositories.interfaces.CacheProfileDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
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

}