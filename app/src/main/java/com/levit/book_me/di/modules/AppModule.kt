package com.levit.book_me.di.modules

import com.levit.book_me.application.BookMeApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: BookMeApplication) = app.applicationContext
}
