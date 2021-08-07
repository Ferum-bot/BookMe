package com.levit.book_me.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.ui.fragments.utills.BackPressedOwner

abstract class BaseActivity: AppCompatActivity() {

    protected val appComponent by lazy {
        val application = application as BookMeApplication
        return@lazy application.appComponent
    }

    protected val bookMeApp: BookMeApplication
    get() = application as BookMeApplication

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        var backPressedHandled = false
        fragments.mapNotNull { fragment ->
            fragment as? BackPressedOwner
        }.forEach { fragment ->
            backPressedHandled = backPressedHandled or fragment.onBackPressed()
        }
        if (!backPressedHandled) {
            super.onBackPressed()
        }
    }
}