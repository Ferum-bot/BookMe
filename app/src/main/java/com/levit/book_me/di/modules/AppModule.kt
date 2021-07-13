package com.levit.book_me.di.modules

import android.content.SharedPreferences
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core_network.model.NetworkMonitor
import com.levit.book_me.di.DIConstants
import com.levit.bookme.uikit.models.NotificationManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: BookMeApplication) = app.applicationContext

    @Provides
    @Singleton
    fun provideNetworkMonitor(app: BookMeApplication): NetworkMonitor {
        return NetworkMonitor(app)
    }
}
