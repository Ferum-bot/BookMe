package com.levit.book_me.core.di.modules.onboarding

import com.levit.book_me.core.interactors.implementations.OnBoardingInteractorImpl
import com.levit.book_me.core.interactors.interfaces.OnBoardingInteractor
import com.levit.book_me.core.repositories.FirstLaunchRepository
import dagger.Module
import dagger.Provides

@Module
open class OnBoardingInteractorsModule {

    @Provides
    fun provideSplashAndOnBoardingInteractor(firstLaunchRepository: FirstLaunchRepository): OnBoardingInteractor {
        return OnBoardingInteractorImpl(firstLaunchRepository)
    }

}