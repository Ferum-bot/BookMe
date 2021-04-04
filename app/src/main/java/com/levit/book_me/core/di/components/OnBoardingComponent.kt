package com.levit.book_me.core.di.components

import com.levit.book_me.core.di.modules.InteractorsModule
import com.levit.book_me.core.di.modules.OnBoardingViewModelsModule
import com.levit.book_me.core.di.modules.RepositoryModule
import com.levit.book_me.core_base.di.OnBoardingScope
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import com.levit.book_me.ui.activities.onboarding.OnBoardingActivity
import dagger.Subcomponent

@OnBoardingScope
@Subcomponent(modules = [
    OnBoardingViewModelsModule::class,
    RepositoryModule::class,
    InteractorsModule::class,
])
interface OnBoardingComponent {

    fun inject(activity: OnBoardingActivity)

    fun viewModelFactory(): ViewModelFactory

    @Subcomponent.Builder
    interface Builder {


        fun build(): OnBoardingComponent
    }
}