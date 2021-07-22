package com.levit.book_me.core.extensions

import android.app.Service
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.di.components.AppComponent

val Service.appComponent: AppComponent
    get() {
        val app = application as BookMeApplication
        return app.appComponent
    }