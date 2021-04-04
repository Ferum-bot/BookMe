package com.levit.book_me.core.di.components

import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.modules.*
import com.levit.book_me.core_presentation.utils.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        AppSubComponents::class,
    ]
)
interface AppComponent {
    fun inject(app: BookMeApplication)

    fun authorizationComponent(): AuthorizationComponent.Builder
    fun onBoardingComponent(): OnBoardingComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: BookMeApplication): Builder

        fun build(): AppComponent
    }
}
