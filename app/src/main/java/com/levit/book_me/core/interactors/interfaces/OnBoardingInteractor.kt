package com.levit.book_me.core.interactors.interfaces

import com.levit.book_me.core_base.di.OnBoardingScope

@OnBoardingScope
interface OnBoardingInteractor {

    fun isFirstLaunch(): Boolean

    fun firstLaunchHappened()

}