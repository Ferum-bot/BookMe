package com.levit.book_me.ui.fragments.creating_profile.creating_books_want_to_read

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentCreatingBooksYouWantToReadBinding
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingBooksAdapter

class CreatingBooksWantToReadFragment:
    BaseCreatingProfileFragment(R.layout.fragment_creating_books_you_want_to_read), CreatingBooksAdapter.CreatingBooksClickListener {

    companion object {
        private const val FRAGMENT_POSITION = 6
    }

    private val binding by viewBinding { FragmentCreatingBooksYouWantToReadBinding.bind(it) }

    private val viewModel by viewModels<CreatingBooksWantToReadViewModel> { creatingProfileComponent.viewModelFactory() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updatePageIndicator()
        setAllClickListeners()
        setAllObservers()
    }

    override fun onBookClicked(newState: CreatingBooksAdapter.CreatingBooksStates, book: GoogleBook) {

    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun setAllClickListeners() {

    }

    private fun setAllObservers() {

    }
}