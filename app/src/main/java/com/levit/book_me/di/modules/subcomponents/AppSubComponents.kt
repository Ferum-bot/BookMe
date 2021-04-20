package com.levit.book_me.di.modules.subcomponents

import com.levit.book_me.di.components.AuthorizationComponent
import com.levit.book_me.di.components.CreatingProfileComponent
import com.levit.book_me.di.components.OnBoardingComponent
import dagger.Module

@Module(subcomponents = [
    AuthorizationComponent::class,
    OnBoardingComponent::class,
    CreatingProfileComponent::class,
])
open class AppSubComponents