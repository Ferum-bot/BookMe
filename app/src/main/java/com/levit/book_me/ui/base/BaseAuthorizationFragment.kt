package com.levit.book_me.ui.base

import androidx.annotation.LayoutRes
import com.levit.book_me.R
import com.levit.book_me.core_network.model.NetworkMonitor
import com.levit.book_me.core_presentation.base.BaseFragment

abstract class BaseAuthorizationFragment(
    @LayoutRes
    id: Int
): BaseFragment(id) {

    protected fun isNetworkAvailable(): Boolean {
        return NetworkMonitor.isNetworkAvailable
    }

    protected fun networkNotAvailableAndShowError(): Boolean {
        if (!NetworkMonitor.isNetworkAvailable) {
            showError(R.string.network_not_available)
        }
        return !isNetworkAvailable()
    }
}