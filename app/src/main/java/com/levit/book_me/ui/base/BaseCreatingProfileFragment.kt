package com.levit.book_me.ui.base

import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.levit.book_me.R
import com.levit.book_me.core_network.model.NetworkMonitor
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.activities.creating_profile.TitleViewController

abstract class BaseCreatingProfileFragment<VM: BaseViewModel>(
    @LayoutRes
    id: Int
): BaseFragment(id) {

    protected abstract val viewModel: VM

    protected val creatingProfileComponent by lazy {
        val activity = requireActivity() as CreatingProfileActivity
        return@lazy activity.creatingProfileComponent
    }

    protected fun showMainTitle(show: Boolean) {
        val activity = requireActivity() as CreatingProfileActivity
        val titleController = activity as TitleViewController
        titleController.showTitle(show)
    }

    protected open fun setAllObservers() {
        viewModel.errorMessageId.observe(viewLifecycleOwner, Observer { messageId ->
            if (messageId != null) {
                showError(messageId)
                viewModel.errorMessageHasShown()
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message != null) {
                showError(message)
                viewModel.errorMessageHasShown()
            }
        })
    }
}