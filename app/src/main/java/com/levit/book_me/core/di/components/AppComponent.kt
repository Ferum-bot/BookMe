package com.levit.book_me.core.di.components

import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.modules.AppModule
import com.levit.book_me.core.di.modules.DatabaseModule
import com.levit.book_me.core.di.modules.NetworkModule
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
        DatabaseModule::class
    ]
)
interface AppComponent {
    fun inject(app: BookMeApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: BookMeApplication): Builder

        fun build(): AppComponent
    }
}
