package com.levit.book_me.di

import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.di.components.AppComponent
import com.levit.book_me.di.components.DaggerAppComponent

object Injector {

    private var _appComponent: AppComponent? = null

    fun initAppComponent(app: BookMeApplication) {
        _appComponent = DaggerAppComponent.builder()
            .application(app)
            .build()
    }

    val appComponent: AppComponent
    get() = checkNotNull(_appComponent) { "App Component is not initialized!" }
}