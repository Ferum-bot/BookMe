package com.levit.book_me.core.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.levit.book_me.core_base.extensions.setTextToClipboard
import com.levit.book_me.ui.delegates.FragmentBindingDelegate

fun <T: ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentBindingDelegate(this, viewBindingFactory)

fun Fragment.setTextToClipboard(title: String = "", getText: () -> String) {
    val context = requireContext()
    context.setTextToClipboard(title, getText)
}