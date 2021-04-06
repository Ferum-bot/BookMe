package com.levit.book_me.core.di.components

import com.levit.book_me.core.di.modules.onboarding.OnBoardingInteractorsModule
import com.levit.book_me.core.di.modules.onboarding.OnBoardingViewModelsModule
import com.levit.book_me.core_base.di.OnBoardingScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.ui.activities.onboarding.OnBoardingActivity
import dagger.Subcomponent

@OnBoardingScope
@Subcomponent(modules = [
    OnBoardingViewModelsModule::class,
    OnBoardingInteractorsModule::class,
    OnBoardingInteractorsModule::class,
])
interface OnBoardingComponent {

    fun inject(activity: OnBoardingActivity)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {


        fun build(): OnBoardingComponent
    }
}