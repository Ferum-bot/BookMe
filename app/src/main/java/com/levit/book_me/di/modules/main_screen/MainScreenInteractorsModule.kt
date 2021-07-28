package com.levit.book_me.di.modules.main_screen

import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.interactors.main_screen.*
import com.levit.book_me.interactors.main_screen.impl.*
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import com.levit.book_me.repositories.profile.ProfileRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
open class MainScreenInteractorsModule {

    @Provides
    @Named(DIConstants.MAIN_SCREEN_TEST_PROFILE_INTERACTOR)
    fun provideTestUserProfileInteractor(
        cacheDataSource: CacheProfileDataSource,
        uploadProfileDataSource: FirebaseStorageUploadUriRepository
    ): UserProfileInteractor {
        return TestUserProfileInteractor(cacheDataSource, uploadProfileDataSource)
    }

    @Provides
    @Named(DIConstants.MAIN_SCREEN_BASE_PROFILE_INTERACTOR)
    fun provideBaseUserProfileInteractor(
        repository: ProfileRepository
    ): UserProfileInteractor {
        return UserProfileInteractorImpl(repository)
    }

    @Provides
    fun provideCurrentFriendInteractor(
        repository: ProfileRepository
    ): CurrentFriendInteractor {
        return TestCurrentFriendInterator(repository)
    }

    @Provides
    fun provideChatsInteractor(): ChatsInteractor {
        return TestChatsInteractor()
    }

    @Provides
    fun provideCurrentChatInteractor(): CurrentChatInteractor {
        return TestCurrentChatInteractor()
    }

    @Provides
    fun provideMainScreenInteractor(): MainScreenInteractor {
        return TestMainScreenInteractor()
    }
}