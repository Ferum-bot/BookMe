package com.levit.book_me.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.di.DIConstants
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
open class SharedPreferenceModule {

    @Named(DIConstants.PROFILE_SHARED_PREFERENCE_NAME)
    @Provides
    fun provideProfileSharedPreference(app: BookMeApplication): SharedPreferences {
        val context = app.applicationContext
        return context.getSharedPreferences(
            DIConstants.PROFILE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE,
        )
    }

}