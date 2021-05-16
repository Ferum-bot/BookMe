package com.levit.book_me.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.levit.book_me.R
import com.levit.book_me.core_network.model.NetworkMonitor
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivityViewModel
import com.levit.book_me.ui.activities.creating_profile.TitleViewController

abstract class BaseCreatingProfileFragment<VM: BaseViewModel>(
    @LayoutRes
    id: Int
): BaseFragment(id) {

    protected abstract val viewModel: VM

    protected val sharedViewModel by activityViewModels<CreatingProfileActivityViewModel> {
        creatingProfileComponent.viewModelFactory()
    }

    protected val creatingProfileComponent by lazy {
        val activity = requireActivity() as CreatingProfileActivity
        return@lazy activity.creatingProfileComponent
    }

    protected fun showMainPageIndicator(show: Boolean) {
        val activity = requireActivity() as CreatingProfileActivity
        val titleController = activity as TitleViewController
        titleController.showTitle(show)
    }

    protected open fun setAllObservers() {
        viewModel.errorMessageId.observe(viewLifecycleOwner, { messageId ->
            if (messageId != null) {
                showError(messageId)
                viewModel.errorMessageHasShown()
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, { message ->
            if (message != null) {
                showError(message)
                viewModel.errorMessageHasShown()
            }
        })
    }
}