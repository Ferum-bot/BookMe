package com.levit.book_me.ui.fragments.creating_profile.creating_books_want_to_read

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.levit.book_me.R
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentCreatingBooksYouWantToReadBinding
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingBooksAdapter
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingBooksOffsetDecorator

class CreatingBooksWantToReadFragment:
    BaseCreatingProfileFragment<CreatingBooksWantToReadViewModel>(R.layout.fragment_creating_books_you_want_to_read),
    CreatingBooksAdapter.CreatingBooksClickListener {

    companion object {
        private const val FRAGMENT_POSITION = 6
    }

    override val viewModel by viewModels<CreatingBooksWantToReadViewModel> { creatingProfileComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentCreatingBooksYouWantToReadBinding.bind(it) }

    private val booksAdapter by lazy { CreatingBooksAdapter(this) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updatePageIndicator()
        setAllClickListeners()
        setAllObservers()
        configureLayout()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.mostChosenBooks.observe(viewLifecycleOwner, Observer { books ->
            booksAdapter.submitList(books)
        })

        viewModel.currentStatus.observe(viewLifecycleOwner, Observer { status ->
            when(status) {
                CreatingBooksWantToReadViewModel.Statuses.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.currentBooksRecycler.visibility = View.GONE
                    binding.errorLabel.visibility = View.GONE
                    binding.searchView.isEnabled = false
                }
                CreatingBooksWantToReadViewModel.Statuses.LOADED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.currentBooksRecycler.visibility = View.VISIBLE
                    binding.errorLabel.visibility = View.GONE
                    binding.searchView.isEnabled = true
                }
                CreatingBooksWantToReadViewModel.Statuses.ERROR -> {
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
            CreatingBooksAdapter.CreatingBooksStates.CHOSEN -> {
                viewModel.addChosenBook(book)
            }
            CreatingBooksAdapter.CreatingBooksStates.NOT_CHOSEN -> {
                viewModel.removeChosenBook(book)
            }
        }
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun setAllClickListeners() {
        binding.finishButton.setOnClickListener {
            navigateToMainScreen()
        }
    }

    private fun configureLayout() {
        val decorator = CreatingBooksOffsetDecorator()

        with(binding.currentBooksRecycler) {
            adapter = booksAdapter
            addItemDecoration(decorator)
        }

        binding.errorLabel.addClickableText(R.string.try_again) {
            viewModel.getMostChosenBooks()
        }
    }

    private fun navigateToMainScreen() {

    }
}