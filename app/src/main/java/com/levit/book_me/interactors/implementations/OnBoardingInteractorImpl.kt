package com.levit.book_me.interactors.implementations

import com.levit.book_me.interactors.interfaces.OnBoardingInteractor
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