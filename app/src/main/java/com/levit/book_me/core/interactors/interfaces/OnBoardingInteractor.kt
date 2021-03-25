package com.levit.book_me.core.interactors.interfaces

interface OnBoardingInteractor {

    fun isFirstLaunch(): Boolean

    fun firstLaunchHappened()

}