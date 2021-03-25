package com.levit.book_me.core.interactors.implementations

import com.levit.book_me.core.interactors.interfaces.OnBoardingInteractor
import com.levit.book_me.core.repositories.FirstLaunchRepository
import javax.inject.Inject

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