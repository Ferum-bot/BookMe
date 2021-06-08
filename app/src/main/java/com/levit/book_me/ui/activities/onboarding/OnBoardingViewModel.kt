package com.levit.book_me.ui.activities.onboarding

import androidx.lifecycle.ViewModel
import com.levit.book_me.interactors.interfaces.OnBoardingInteractor
import com.levit.book_me.core_base.di.OnBoardingScope
import javax.inject.Inject

@OnBoardingScope
class OnBoardingViewModel @Inject constructor(
    private val interator: OnBoardingInteractor
): ViewModel() {

    fun isFirstLaunch(): Boolean {
        val result = interator.isFirstLaunch()
        interator.firstLaunchHappened()
        return result
    }

}