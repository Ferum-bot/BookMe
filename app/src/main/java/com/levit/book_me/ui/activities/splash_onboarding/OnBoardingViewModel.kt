package com.levit.book_me.ui.activities.splash_onboarding

import androidx.lifecycle.ViewModel
import com.levit.book_me.core.interactors.interfaces.OnBoardingInteractor
import javax.inject.Inject

class OnBoardingViewModel @Inject constructor(
    private val interator: OnBoardingInteractor
): ViewModel() {

    fun isFirstLaunch(): Boolean {
        val result = interator.isFirstLaunch()
        interator.firstLaunchHappened()
        return result
    }

}