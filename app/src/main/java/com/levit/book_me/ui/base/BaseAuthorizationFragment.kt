package com.levit.book_me.ui.base

import androidx.annotation.LayoutRes
import com.levit.book_me.R
import com.levit.book_me.core_network.model.NetworkMonitor
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.ui.activities.authorization.AuthorizationActivity

abstract class BaseAuthorizationFragment(
    @LayoutRes
    id: Int
): BaseFragment(id) {

    protected val authorizationComponent by lazy {
        val activity = requireActivity() as AuthorizationActivity
        return@lazy activity.authorizationComponent
    }

    protected fun isNetworkAvailable(): Boolean {
        return NetworkMonitor.isNetworkAvailable
    }

    protected fun networkNotAvailableAndShowError(): Boolean {
        val networkIsNotAvailable = !NetworkMonitor.isNetworkAvailable
        if (networkIsNotAvailable) {
            showError(R.string.network_not_available)
        }
        return networkIsNotAvailable
    }
}