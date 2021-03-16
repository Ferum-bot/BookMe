package com.levit.book_me.core_presentation.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    /**
     * Add lately custom Toasts to different
     * types of messages
     */
    protected fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    protected fun showError(@StringRes id: Int) {
        val errorMessage = getString(id)
        showError(errorMessage)
    }

    protected fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun showMessage(@StringRes id: Int) {
        val message = getString(id)
        showMessage(message)
    }

    protected fun showSuccessMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun showSuccessMessage(@StringRes id: Int) {
        val message = getString(id)
        showSuccessMessage(message)
    }
}
