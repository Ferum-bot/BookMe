package com.levit.book_me.di.modules.main_screen

import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
import com.levit.book_me.interactors.main_screen.impl.TestUserProfileInteractor
import dagger.Module
import dagger.Provides

@Module
open class MainScreenInteractorsModule {

    @Provides
    fun provideUserProfileInteractor(dataSource: CacheProfileDataSource): UserProfileInteractor {
        return TestUserProfileInteractor(dataSource)
    }

}