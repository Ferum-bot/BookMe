package com.levit.book_me.core.interfaces

import androidx.annotation.StringRes

interface ResourceProvider {

    fun string(@StringRes id: Int): String

}