package com.levit.book_me.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.activityViewModels
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.ui.activities.main_screen.MainScreenActivity
import com.levit.book_me.ui.activities.main_screen.MainScreenActivityViewModel

abstract class BaseMainScreenFragment<VM: BaseViewModel>(
    @LayoutRes
    id: Int,
): BaseFragment(id) {

    protected abstract val viewModel: VM

    protected val sharedViewModel by activityViewModels<MainScreenActivityViewModel> {
        mainScreenComponent.viewModelFactory()
    }

    protected val mainScreenComponent by lazy {
        val activity = requireActivity() as MainScreenActivity
        return@lazy activity.mainScreenComponent
    }

    protected open fun setAllObservers() {
        viewModel.errorMessageId.observe(viewLifecycleOwner) { messageId ->
            if (messageId != null) {
                showError(messageId)
                viewModel.errorMessageHasShown()
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                showError(message)
                viewModel.errorMessageHasShown()
            }
        }
    }
}