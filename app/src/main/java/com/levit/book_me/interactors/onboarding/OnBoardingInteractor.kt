package com.levit.book_me.interactors.onboarding

import com.levit.book_me.core.enums.profile.CurrentUserStatus
import com.levit.book_me.core_base.di.OnBoardingScope

@OnBoardingScope
interface OnBoardingInteractor {

    fun isFirstLaunch(): Boolean

    fun firstLaunchHappened()

    fun getCurrentUserStatus(): CurrentUserStatus
}