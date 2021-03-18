package com.levit.book_me.core.implementations

import android.content.Context
import com.levit.book_me.core.interfaces.ResourceProvider
import javax.inject.Inject

class AndroidResourceProvider @Inject constructor(
    appContext: Context
): ResourceProvider {

    private val resources = appContext.resources

    override fun string(id: Int): String =
        resources.getString(id)
}