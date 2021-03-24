package com.levit.book_me.core.interactors.implementations

import com.levit.book_me.core.interactors.interfaces.SplashAndOnBoardingInteractor
import com.levit.book_me.core.repositories.FirstLaunchRepository
import javax.inject.Inject

class SplashAndOnBoardingInteractorImpl @Inject constructor(
    private val repository: FirstLaunchRepository
): SplashAndOnBoardingInteractor {

    override fun isFirstLaunch(): Boolean {
        return repository.getFirstLaunch()
    }

    override fun firstLaunchHappened() {
        repository.setFirstLaunch(false)
    }
}