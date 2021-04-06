package com.levit.book_me.core.di.modules

import com.levit.book_me.core.di.components.AuthorizationComponent
import com.levit.book_me.core.di.components.CreatingProfileComponent
import com.levit.book_me.core.di.components.OnBoardingComponent
import dagger.Module

@Module(subcomponents = [
    AuthorizationComponent::class,
    OnBoardingComponent::class,
    CreatingProfileComponent::class,
])
open class AppSubComponents