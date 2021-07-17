package com.levit.book_me.di.modules

import android.content.SharedPreferences
import com.levit.book_me.data_sources.google.GoogleBooksVolumeDataSource
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.data_sources.profile.ProfileRemoteDataSourceFacade
import com.levit.book_me.data_sources.profile.RegisterNewUserDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.repositories.profile.impl.GenresRepositoryMock
import com.levit.book_me.repositories.google.impl.SearchAuthorsRepositoryImpl
import com.levit.book_me.repositories.google.impl.SearchBooksRepositoryImpl
import com.levit.book_me.repositories.profile.GenresRepository
import com.levit.book_me.repositories.google.SearchAuthorsRepository
import com.levit.book_me.repositories.google.SearchBooksRepository
import com.levit.book_me.repositories.profile.AuthRepository
import com.levit.book_me.repositories.profile.ProfileRepository
import com.levit.book_me.repositories.profile.RegisterNewUserRepository
import com.levit.book_me.repositories.profile.impl.ProfileRepositoryImpl
import com.levit.book_me.repositories.profile.impl.RegisterNewUserRepositoryImpl
import com.levit.book_me.repositories.profile.impl.SharedPrefAuthRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module
open class RepositoryModule {

    @Provides
    fun provideSearchAuthorsRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        dataSource: GoogleBooksVolumeDataSource
    ): SearchAuthorsRepository {
        return SearchAuthorsRepositoryImpl(coroutineContext, dataSource)
    }

    @Provides
    fun provideSearchBooksRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        dataSource: GoogleBooksVolumeDataSource
    ): SearchBooksRepository {
        return SearchBooksRepositoryImpl(coroutineContext, dataSource)
    }

    @Provides
    fun provideGenresRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,
    ): GenresRepository {
        return GenresRepositoryMock(coroutineContext)
    }

    @Provides
    fun provideRegisterNewUserRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        localDataSource: CacheProfileDataSource,
        remoteDataSource: RegisterNewUserDataSource,
    ): RegisterNewUserRepository {
        return RegisterNewUserRepositoryImpl(coroutineContext, localDataSource, remoteDataSource)
    }

    @Provides
    fun provideProfileRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        localDataSource: CacheProfileDataSource,
        remoteDataSourceFacade: ProfileRemoteDataSourceFacade,
    ): ProfileRepository {
        return ProfileRepositoryImpl(coroutineContext, localDataSource, remoteDataSourceFacade)
    }

    @Provides
    fun provideAuthRepository(
        @Named(DIConstants.IO_DISPATCHER_CONTEXT)
        coroutineContext: CoroutineContext,

        @Named(DIConstants.AUTH_INFO_SHARED_PREFERENCE_NAME)
        sharedPreferences: SharedPreferences,
    ): AuthRepository {
        return SharedPrefAuthRepository(coroutineContext, sharedPreferences)
    }
}