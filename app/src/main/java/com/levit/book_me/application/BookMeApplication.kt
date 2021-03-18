package com.levit.book_me.application

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.levit.book_me.R
import com.levit.book_me.core.di.components.AppComponent
import com.levit.book_me.core.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BookMeApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)

        configureFacebookSdk()
    }

    /**
     * Marked deprecated but without it
     * app crashes
     */
    private fun configureFacebookSdk() {
        val facebookAppId = getString(R.string.facebook_app_id)
        FacebookSdk.setApplicationId(facebookAppId)
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)
    }
}
