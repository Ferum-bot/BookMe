package com.levit.book_me.core_presentation.base

import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.levit.book_me.core_presentation.R

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    /**
     * Add lately custom Toasts to different
     * types of messages
     */

    private val errorColor
    get() = Color.rgb(255, 68, 68)

    private val successColor
    get() = Color.rgb(153, 204, 0)

    private val defaultColor
    get() = Color.rgb(51, 181, 229)

    protected fun showError(errorMessage: String) {
        Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_SHORT).apply {
            setBackgroundTint(errorColor)
            setTextColor(Color.BLACK)
            animationMode = Snackbar.ANIMATION_MODE_SLIDE

            val textView = this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
        .show()
    }

    protected fun showError(@StringRes id: Int) {
        val errorMessage = getString(id)
        showError(errorMessage)
    }

    protected fun showMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).apply {
            setBackgroundTint(defaultColor)
            setTextColor(Color.BLACK)
            animationMode = Snackbar.ANIMATION_MODE_SLIDE

            val textView = this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
        .show()
    }

    protected fun showMessage(@StringRes id: Int) {
        val message = getString(id)
        showMessage(message)
    }

    protected fun showSuccessMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).apply {
            setBackgroundTint(successColor)
            setTextColor(Color.BLACK)
            animationMode = Snackbar.ANIMATION_MODE_SLIDE

            val textView = this.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
        .show()
    }

    protected fun showSuccessMessage(@StringRes id: Int) {
        val message = getString(id)
        showSuccessMessage(message)
    }
}
