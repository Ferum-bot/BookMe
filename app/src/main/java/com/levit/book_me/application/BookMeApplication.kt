package com.levit.book_me.application

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.levit.book_me.BuildConfig
import com.levit.book_me.R
import com.levit.book_me.core.utill.NotificationsUtil
import com.levit.book_me.di.components.AppComponent
import com.levit.book_me.core_network.model.NetworkMonitor
import com.levit.book_me.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class BookMeApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    lateinit var appComponent: AppComponent

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)

        configureFacebookSdk()
        configureFirebase()
        registerNetworkMonitor()
        initTimber()
        createNotificationChannels()
    }

    override fun onTerminate() {
        super.onTerminate()

        removeNetworkMonitor()
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

    private fun configureFirebase() {
        Firebase.auth.useAppLanguage()
    }

    private fun registerNetworkMonitor() {
        com.levit.book_me.network.utill.NetworkMonitor.startListening(this)
        networkMonitor.startMonitoringNetwork()
    }

    private fun removeNetworkMonitor() {
        com.levit.book_me.network.utill.NetworkMonitor.stopListening(this)
        networkMonitor.stopMonitoringNetwork()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createInfoChannel()
            createMessagesChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createInfoChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name = getString(R.string.info_notification_channel_name)
        val description = getString(R.string.info_notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val id = NotificationsUtil.INFO_CHANNEL_ID

        val channel = NotificationChannel(id, name, importance).apply {
            this.description = description
        }
        notificationManager.createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createMessagesChannel() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name = getString(R.string.message_notification_channel_name)
        val description = getString(R.string.message_notification_channel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val id = NotificationsUtil.MESSAGE_CHANNEL_ID

        val channel = NotificationChannel(id, name, importance).apply {
            this.description = description
        }
        notificationManager.createNotificationChannel(channel)
    }
}
