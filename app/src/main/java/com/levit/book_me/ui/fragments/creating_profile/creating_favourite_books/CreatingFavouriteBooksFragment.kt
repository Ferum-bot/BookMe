package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_books

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentCreatingFavouriteBooksBinding
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingBooksAdapter
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingBooksOffsetDecorator

class CreatingFavouriteBooksFragment:
    BaseCreatingProfileFragment<CreatingFavouriteBooksViewModel>(R.layout.fragment_creating_favourite_books),
    CreatingBooksAdapter.CreatingBooksClickListener {

    companion object {
        private const val FRAGMENT_POSITION = 5
    }

    override val viewModel by viewModels<CreatingFavouriteBooksViewModel> { creatingProfileComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentCreatingFavouriteBooksBinding.bind(it) }

    private val adapter by lazy { CreatingBooksAdapter(this) }

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

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.popularBooks.observe(viewLifecycleOwner, Observer { books ->
            adapter.submitList(books)
        })

        viewModel.currentStatus.observe(viewLifecycleOwner, Observer { status ->
            when(status) {
                CreatingFavouriteBooksViewModel.Statuses.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.currentBooksRecycler.visibility = View.GONE
                    binding.errorLabel.visibility = View.GONE
                    binding.searchView.isEnabled = false
                }
                CreatingFavouriteBooksViewModel.Statuses.LOADED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.currentBooksRecycler.visibility = View.VISIBLE
                    binding.errorLabel.visibility = View.GONE
                    binding.searchView.isEnabled = true
                }
                CreatingFavouriteBooksViewModel.Statuses.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.currentBooksRecycler.visibility = View.GONE
                    binding.errorLabel.visibility = View.VISIBLE
                    binding.searchView.isEnabled = false
                }
            }
        })
    }

    override fun onBookClicked(newState: CreatingBooksAdapter.CreatingBooksStates, book: GoogleBook) {
        when(newState) {
            CreatingBooksAdapter.CreatingBooksStates.CHOSEN ->
                viewModel.addChosenBook(book)
            CreatingBooksAdapter.CreatingBooksStates.NOT_CHOSEN ->
                viewModel.removeChosenBook(book)
        }
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun configureAllViews() {
        val booksDecorator = CreatingBooksOffsetDecorator()
        with(binding.currentBooksRecycler) {
            adapter = this@CreatingFavouriteBooksFragment.adapter
            addItemDecoration(booksDecorator)
        }

        binding.errorLabel.addClickableText(R.string.try_again) {
            viewModel.getPopularBooks()
        }
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
        val action = CreatingFavouriteBooksFragmentDirections
            .actionCreatingFavouriteBooksFragmentToSearchBooksFragment()
        findNavController().navigate(action)
    }
}