package com.levit.book_me.di.components

import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.di.modules.*
import com.levit.book_me.di.modules.subcomponents.AppSubComponents
import com.levit.book_me.repositories.profile.AuthRepository
import com.levit.book_me.services.FirebaseService
import com.levit.book_me.work_managers.SafeFCMTokenWorker
import com.levit.book_me.work_managers.UploadFCMTokenWorker
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DatabaseModule::class,
        AppSubComponents::class,
        SharedPreferenceModule::class,
    ]
)
interface AppComponent {

    fun inject(app: BookMeApplication)

    fun authorizationComponent(): AuthorizationComponent.Builder
    fun onBoardingComponent(): OnBoardingComponent.Builder
    fun creatingProfileComponent(): CreatingProfileComponent.Builder
    fun mainScreenComponent(): MainScreenComponent.Builder

    val authRepository: AuthRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: BookMeApplication): Builder

        fun build(): AppComponent
    }
}
