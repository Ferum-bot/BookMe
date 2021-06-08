package com.levit.book_me.di.modules.onboarding

import android.content.Context
import com.levit.book_me.repositories.FirstLaunchRepository
import dagger.Module
import dagger.Provides

@Module
open class OnBoardingRepositoryModule {

    @Provides
    fun provideFirstLaunchRepository(context: Context): FirstLaunchRepository {
        return FirstLaunchRepository(context)
    }

}