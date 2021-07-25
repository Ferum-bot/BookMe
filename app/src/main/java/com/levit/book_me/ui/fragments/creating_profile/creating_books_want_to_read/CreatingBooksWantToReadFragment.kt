package com.levit.book_me.ui.fragments.creating_profile.creating_books_want_to_read

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.enums.books.SearchBooksTypes
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentCreatingBooksYouWantToReadBinding
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivityViewModel
import com.levit.book_me.ui.activities.main_screen.MainScreenActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.utills.BaseBooksAdapter
import com.levit.book_me.ui.fragments.utills.BaseBooksOffsetDecorator
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants

class CreatingBooksWantToReadFragment:
    BaseCreatingProfileFragment<CreatingBooksWantToReadViewModel>(R.layout.fragment_creating_books_you_want_to_read),
    BaseBooksAdapter.BaseBooksClickListener {

    companion object {
        private const val FRAGMENT_POSITION = 6
    }

    override val viewModel by viewModels<CreatingBooksWantToReadViewModel> { creatingProfileComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentCreatingBooksYouWantToReadBinding.bind(it) }

    private val booksAdapter by lazy { BaseBooksAdapter(this) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showMainPageIndicator(true)
        updatePageIndicator()
        setAllClickListeners()
        setAllObservers()
        configureLayout()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.errorMessageId.removeObservers(viewLifecycleOwner)
        viewModel.errorMessageId.observe(viewLifecycleOwner) { messageId ->
            if (messageId == null) {
                return@observe
            }
            if (messageId == R.string.you_can_choose_not_more_books) {
                val errorString = getString(
                    messageId,
                    CreatingProfileConstants.MAX_COUNT_OF_WANT_TO_READ_BOOKS
                )
                showError(errorString)
            } else {
                showError(messageId)
            }
            viewModel.errorMessageHasShown()
        }

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

        viewModel.isChosenEnoughBooks.observe(viewLifecycleOwner) { isChosenEnoughBooks ->
            binding.countBooksErrorLabel.isVisible = !isChosenEnoughBooks
        }

        sharedViewModel.chosenWantToReadBooks.observe(viewLifecycleOwner) { books ->
            viewModel.addChosenBooks(books)
        }

        sharedViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message == null) {
                return@observe
            }
            showError(message)
            sharedViewModel.errorMessageHasShown()
        }

        sharedViewModel.errorMessageId.observe(viewLifecycleOwner) { messageId ->
            if (messageId == null) {
                return@observe
            }
            showError(messageId)
            sharedViewModel.errorMessageHasShown()
        }

        sharedViewModel.creatingProfileStatus.observe(viewLifecycleOwner) { status ->
            when(status) {
                CreatingProfileActivityViewModel.Status.LOADING -> {
                    binding.creatingProfileProgressBar.visibility = View.VISIBLE
                    binding.finishButton.visibility = View.INVISIBLE
                    binding.searchView.isEnabled = false
                }
                CreatingProfileActivityViewModel.Status.ERROR -> {
                    binding.creatingProfileProgressBar.visibility = View.GONE
                    binding.finishButton.visibility = View.VISIBLE
                    binding.searchView.isEnabled = true
                }
                CreatingProfileActivityViewModel.Status.DONE -> {
                    binding.searchView.isEnabled = false
                    binding.creatingProfileProgressBar.visibility = View.GONE

                    val successString = getString(R.string.profile_created_success)
                    showSuccessMessage(successString)
                    navigateToMainScreen()
                }
                CreatingProfileActivityViewModel.Status.NOTHING, null -> {
                    binding.creatingProfileProgressBar.visibility = View.GONE
                    binding.finishButton.visibility = View.VISIBLE
                    binding.searchView.isEnabled = true
                }
            }
        }
    }

    override fun onBookClicked(newState: BaseBooksAdapter.BaseBooksStates, book: GoogleBook) {
        when(newState) {
            BaseBooksAdapter.BaseBooksStates.CHOSEN -> {
                viewModel.addChosenBook(book)
                sharedViewModel.addChosenBook(
                    type = SearchBooksTypes.BOOKS_YOU_WANT_TO_RED,
                    book = book
                )
            }
            BaseBooksAdapter.BaseBooksStates.NOT_CHOSEN -> {
                viewModel.removeChosenBook(book)
                sharedViewModel.removeChosenBook(
                    type = SearchBooksTypes.BOOKS_YOU_WANT_TO_RED,
                    book = book
                )
            }
        }
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun setAllClickListeners() {
        binding.finishButton.setOnClickListener {
            if (viewModel.everythingIsValid()) {
                sharedViewModel.registerNewUser()
            }
        }

        binding.searchView.setOnClickListener {
            showMainPageIndicator(false)
            navigateToSearchBooksScreen()
        }
    }

    private fun configureLayout() {
        val decorator = BaseBooksOffsetDecorator()

        with(binding.currentBooksRecycler) {
            adapter = booksAdapter
            addItemDecoration(decorator)
        }

        binding.errorLabel.addClickableText(R.string.try_again) {
            viewModel.getMostChosenBooks()
        }

        val labelDescription = getString(
            R.string.choose_from_to_books,
            CreatingProfileConstants.MIN_COUNT_OF_WANT_TO_READ_BOOKS.toString(),
            CreatingProfileConstants.MAX_COUNT_OF_WANT_TO_READ_BOOKS.toString()
        )
        val errorCountBooksLabel = getString(
            R.string.choose_at_least_books,
            CreatingProfileConstants.MIN_COUNT_OF_WANT_TO_READ_BOOKS.toString()
        )
        binding.labelDescription.text = labelDescription
        binding.countBooksErrorLabel.text = errorCountBooksLabel
    }

    private fun navigateToMainScreen() {
        val intent = Intent(requireContext(), MainScreenActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun navigateToSearchBooksScreen() {
        val action = CreatingBooksWantToReadFragmentDirections
            .actionCreatingBooksWantToReadFragmentToSearchBooksFragment(
                searchType = SearchBooksTypes.BOOKS_YOU_WANT_TO_RED
            )
        findNavController().navigate(action)
    }
}