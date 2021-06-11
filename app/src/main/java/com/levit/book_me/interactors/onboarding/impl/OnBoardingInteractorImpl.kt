package com.levit.book_me.interactors.onboarding.impl

import com.levit.book_me.interactors.onboarding.OnBoardingInteractor
import com.levit.book_me.repositories.FirstLaunchRepository
import com.levit.book_me.core_base.di.OnBoardingScope
import javax.inject.Inject

@OnBoardingScope
class OnBoardingInteractorImpl @Inject constructor(
    private val repository: FirstLaunchRepository
): OnBoardingInteractor {

    override fun isFirstLaunch(): Boolean {
        return repository.getFirstLaunch()
    }

    override fun firstLaunchHappened() {
        repository.setFirstLaunch(false)
    }
}