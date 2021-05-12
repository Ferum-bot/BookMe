package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_books

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentCreatingFavouriteBooksBinding
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingBooksAdapter

class CreatingFavouriteBooksFragment:
    BaseCreatingProfileFragment(R.layout.fragment_creating_favourite_books), CreatingBooksAdapter.CreatingBooksClickListener {

    companion object {
        private const val FRAGMENT_POSITION = 5
    }

    private val binding by viewBinding { FragmentCreatingFavouriteBooksBinding.bind(it) }

    private val viewModel by viewModels<CreatingFavouriteBooksViewModel> { creatingProfileComponent.viewModelFactory() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updatePageIndicator()
        configureAllViews()
        setAllObservers()
        setAllClickListeners()
    }

    override fun onBookClicked(newState: CreatingBooksAdapter.CreatingBooksStates, book: GoogleBook) {

    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun configureAllViews() {
        binding.searchView.isEnabled = false
    }

    private fun setAllObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message != null) {
                showError(message)
                viewModel.errorMessageHasShown()
            }
        })

        viewModel.errorMessageId.observe(viewLifecycleOwner, Observer { messageId ->
            if (messageId != null) {
                showError(messageId)
                viewModel.errorMessageHasShown()
            }
        })
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            navigateToCreatingBooksWantToReadFragment()
        }

        binding.searchView.setOnClickListener {
            navigateToSearchBooksFragment()
        }
    }

    private fun navigateToCreatingBooksWantToReadFragment() {
        val action = CreatingFavouriteBooksFragmentDirections
            .actionCreatingFavouriteBooksFragmentToCreatingBooksWantToReadFragment()
        findNavController().navigate(action)
    }

    private fun navigateToSearchBooksFragment() {

    }
}