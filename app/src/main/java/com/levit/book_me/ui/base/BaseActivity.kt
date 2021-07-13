package com.levit.book_me.ui.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.levit.book_me.application.BookMeApplication

abstract class BaseActivity: AppCompatActivity() {

    protected val appComponent by lazy {
        val application = application as BookMeApplication
        return@lazy application.appComponent
    }

    protected val bookMeApp: BookMeApplication
    get() = application as BookMeApplication

}