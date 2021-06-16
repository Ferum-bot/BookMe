package com.levit.book_me.interactors.onboarding.impl

import com.levit.book_me.interactors.onboarding.OnBoardingInteractor
import com.levit.book_me.repositories.FirstLaunchRepository
import com.levit.book_me.core_base.di.OnBoardingScope
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@OnBoardingScope
class OnBoardingInteractorImpl @Inject constructor(
    private val repository: FirstLaunchRepository,
    private val dataSource: CacheProfileDataSource,
): OnBoardingInteractor {

    override fun isFirstLaunch(): Boolean {
        return repository.getFirstLaunch()
    }

    override fun firstLaunchHappened() {
        repository.setFirstLaunch(false)
    }

    override fun getCurrentUserStatus() = runBlocking {
        return@runBlocking dataSource.getCurrentStatus()
    }
}