package com.levit.book_me.di.modules.onboarding

import com.levit.book_me.interactors.onboarding.impl.OnBoardingInteractorImpl
import com.levit.book_me.interactors.onboarding.OnBoardingInteractor
import com.levit.book_me.repositories.FirstLaunchRepository
import dagger.Module
import dagger.Provides

@Module
open class OnBoardingInteractorsModule {

    @Provides
    fun provideSplashAndOnBoardingInteractor(firstLaunchRepository: FirstLaunchRepository): OnBoardingInteractor {
        return OnBoardingInteractorImpl(firstLaunchRepository)
    }

}