package com.levit.book_me.core.di.modules

import android.content.Context
import com.levit.book_me.core.interactors.implementations.SplashAndOnBoardingInteractorImpl
import com.levit.book_me.core.interactors.interfaces.SplashAndOnBoardingInteractor
import com.levit.book_me.core.repositories.FirstLaunchRepository
import dagger.Module
import dagger.Provides

@Module
open class InteractorsModule {

    @Provides
    fun provideSplashAndOnBoardingInteractor(firstLaunchRepository: FirstLaunchRepository): SplashAndOnBoardingInteractor {
        return SplashAndOnBoardingInteractorImpl(firstLaunchRepository)
    }
}