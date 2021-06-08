package com.levit.book_me.ui.base

import androidx.annotation.LayoutRes
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.di.components.QuotesComponent
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity

abstract class QuotesBaseFragment(@LayoutRes id: Int): BaseFragment(id) {

    protected lateinit var quotesComponent: QuotesComponent

    protected fun hideCreatingProfileTitle() {
        val activity = requireActivity() as? CreatingProfileActivity
        activity?.showTitle(false)
    }

    protected open fun initDi() {
        val activity = requireActivity()
        if (activity is CreatingProfileActivity) {
            quotesComponent = activity.creatingProfileComponent.quotesComponent().build()
        }
    }
}