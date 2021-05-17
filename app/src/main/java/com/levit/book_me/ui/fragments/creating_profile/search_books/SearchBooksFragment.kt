package com.levit.book_me.ui.fragments.creating_profile.search_books

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.levit.book_me.R
import com.levit.book_me.core.extensions.hideKeyboard
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.databinding.FragmentSearchBooksBinding
import com.levit.book_me.di.components.SearchBooksComponent
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingBooksAdapter
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingBooksOffsetDecorator

class SearchBooksFragment:
    BaseCreatingProfileFragment<SearchBooksViewModel>(R.layout.fragment_search_books),
    CreatingBooksAdapter.CreatingBooksClickListener {

    private lateinit var searchBooksComponent: SearchBooksComponent

    override val viewModel by viewModels<SearchBooksViewModel> { searchBooksComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentSearchBooksBinding.bind(it) }

    private val booksAdapter by lazy { CreatingBooksAdapter(this) }

    private val args by navArgs<SearchBooksFragmentArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initDI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureLayout()
        setAllClickListeners()
        setAllObservers()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.currentStatus.observe(viewLifecycleOwner, { status ->
            when(status) {
                SearchBooksViewModel.Statuses.SEARCHING -> {
                    binding.alertLabel.visibility = View.GONE
                    binding.choseButton.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                SearchBooksViewModel.Statuses.FOUND -> {
                    binding.alertLabel.visibility = View.GONE
                    binding.choseButton.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                SearchBooksViewModel.Statuses.NOTHING_FOUND -> {
                    binding.alertLabel.visibility = View.VISIBLE
                    binding.alertLabel.setText(R.string.nothing_found)
                    binding.choseButton.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                SearchBooksViewModel.Statuses.NOT_SEARCHING -> {
                    binding.alertLabel.visibility = View.GONE
                    binding.choseButton.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                SearchBooksViewModel.Statuses.ERROR -> {
                    binding.alertLabel.visibility = View.VISIBLE
                    binding.alertLabel.setText(R.string.something_went_wrong)
                    binding.choseButton.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        })

        viewModel.searchResult.observe(viewLifecycleOwner, { books ->
            booksAdapter.submitList(books)
        })

        viewModel.someBooksIsChosen.observe(viewLifecycleOwner, { someBooksIsChosen ->
            binding.choseButton.isVisible = someBooksIsChosen
        })
    }

    override fun onBookClicked(newState: CreatingBooksAdapter.CreatingBooksStates, book: GoogleBook) {
        when(newState) {
            CreatingBooksAdapter.CreatingBooksStates.CHOSEN -> {
                viewModel.addChosenBook(book)
                sharedViewModel.addChosenBook(args.searchType, book)
            }
            CreatingBooksAdapter.CreatingBooksStates.NOT_CHOSEN -> {
                viewModel.removeChosenBook(book)
                sharedViewModel.removeChosenBook(args.searchType, book)
            }
        }
    }

    private fun initDI() {
        creatingProfileComponent.inject(this)
        searchBooksComponent = creatingProfileComponent.searchBooksComponent()
            .build()
        searchBooksComponent.inject(this)
    }

    private fun setAllClickListeners() {
        binding.choseButton.setOnClickListener {
            binding.searchView.hideKeyboard()
            popBackStack()
        }
    }

    private fun configureLayout() {
        binding.searchView.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = { text, _, _, _ ->
                viewModel.searchBooks(text.toString())
            }
        })

        val decorator = CreatingBooksOffsetDecorator()
        with(binding.recyclerView) {
            adapter = booksAdapter
            addItemDecoration(decorator)
        }
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }
}