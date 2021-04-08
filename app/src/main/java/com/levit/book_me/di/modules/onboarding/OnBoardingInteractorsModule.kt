package com.levit.book_me.di.modules.onboarding

import com.levit.book_me.interactors.implementations.OnBoardingInteractorImpl
import com.levit.book_me.interactors.interfaces.OnBoardingInteractor
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