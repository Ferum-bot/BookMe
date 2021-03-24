package com.levit.book_me.core.repositories

import android.content.Context
import javax.inject.Inject

class FirstLaunchRepository @Inject constructor(
    context: Context
) {

    private val storage = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getFirstLaunch(): Boolean =
        storage.getBoolean(FIRST_LAUNCH_KEY, true)

    fun setFirstLaunch(isFirstLaunch: Boolean) {
        val editor = storage.edit()
        editor.putBoolean(FIRST_LAUNCH_KEY, isFirstLaunch)
        editor.apply()
    }

    companion object {
        private const val PREFERENCE_NAME = "first_launch_repository"
        private const val FIRST_LAUNCH_KEY = "first_launch_key"
    }
}