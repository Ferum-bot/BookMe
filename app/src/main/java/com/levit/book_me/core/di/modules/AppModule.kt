package com.levit.book_me.core.di.modules

import android.app.Application
import android.content.Context
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.implementations.AndroidResourceProvider
import com.levit.book_me.core.interfaces.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: BookMeApplication) = app.applicationContext

    @Provides
    @Singleton
    fun provideResourceProvider(appContext: Context): ResourceProvider {
        return AndroidResourceProvider(appContext)
    }
}
