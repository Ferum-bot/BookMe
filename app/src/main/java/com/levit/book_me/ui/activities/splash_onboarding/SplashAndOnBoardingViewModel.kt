package com.levit.book_me.ui.activities.splash_onboarding

import androidx.lifecycle.ViewModel
import com.levit.book_me.core.interactors.interfaces.SplashAndOnBoardingInteractor
import javax.inject.Inject

class SplashAndOnBoardingViewModel @Inject constructor(
    private val interator: SplashAndOnBoardingInteractor
): ViewModel() {

    fun isFirstLaunch(): Boolean {
        val result = interator.isFirstLaunch()
        interator.firstLaunchHappened()
        return result
    }

}