package com.levit.book_me.ui.base

import androidx.annotation.LayoutRes
import com.levit.book_me.R
import com.levit.book_me.core_network.model.NetworkMonitor
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity

abstract class BaseCreatingProfileFragment(
    @LayoutRes
    id: Int
): BaseFragment(id) {

    protected val creatingProfileComponent by lazy {
        val activity = requireActivity() as CreatingProfileActivity
        return@lazy activity.creatingProfileComponent
    }

}