package com.levit.book_me.ui.fragments.creating_profile.search_favourites_authors

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.levit.book_me.R
import com.levit.book_me.core.extensions.hideKeyboard
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.FragmentSearchFavouriteAuthorsBinding
import com.levit.book_me.di.components.SearchFavouriteAuthorsComponent
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.bookme.uikit.utills.ParcelableTextWatcher

class SearchFavouriteAuthorsFragment:
    BaseCreatingProfileFragment<SearchFavouriteAuthorsViewModel>(R.layout.fragment_search_favourite_authors),
    SearchFavouriteAuthorsAdapter.AuthorStateListener {

    override val viewModel by viewModels<SearchFavouriteAuthorsViewModel> { searchFavouriteAuthorsComponent.viewModelFactory() }

    private lateinit var searchFavouriteAuthorsComponent: SearchFavouriteAuthorsComponent

    private val binding by viewBinding { FragmentSearchFavouriteAuthorsBinding.bind(it) }

    private val adapter by lazy { SearchFavouriteAuthorsAdapter(this) }

    private val args by navArgs<SearchFavouriteAuthorsFragmentArgs>()

    private val authorPosition by lazy { args.authorPosition }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initDI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setSearchTextChangeListener()
        setAllObservers()
        setAllClickListeners()
    }

    override fun onAuthorStateChanged(author: Author, state: SearchFavouriteAuthorsAdapter.AuthorState) {
        if (state == SearchFavouriteAuthorsAdapter.AuthorState.CHOSEN) {
            safeChosenAuthor(author)
            binding.confirmChoseButton.visibility = View.VISIBLE
        }
        else {
            removeChosenAuthor()
            binding.confirmChoseButton.visibility = View.GONE
        }
    }

    private fun safeChosenAuthor(author: Author) {
        sharedViewModel.safeFavouriteAuthor(
            author = author,
            position = authorPosition
        )
    }

    private fun removeChosenAuthor() {
        sharedViewModel.removeFavouriteAuthor(
            position = authorPosition
        )
    }

    private fun initDI() {
        creatingProfileComponent.inject(this)
        searchFavouriteAuthorsComponent = creatingProfileComponent.searchFavouriteAuthorsComponent()
                .build()
        searchFavouriteAuthorsComponent.inject(this)
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = this@SearchFavouriteAuthorsFragment.adapter
        }
    }

    private fun setSearchTextChangeListener() {
        binding.searchView.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = viewModel::onSearchTextChanged
        })
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.searchResult.observe(viewLifecycleOwner, Observer { listOfAuthors ->
            adapter.submitList(listOfAuthors)
        })

        viewModel.currentStatus.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                SearchFavouriteAuthorsViewModel.SearchStatus.NOT_SEARCHING, null -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.alertTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.confirmChoseButton.visibility = View.GONE
                }
                SearchFavouriteAuthorsViewModel.SearchStatus.SEARCHING -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.alertTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.confirmChoseButton.visibility = View.GONE
                }
                SearchFavouriteAuthorsViewModel.SearchStatus.NOTHING_FOUND -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.alertTextView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.confirmChoseButton.visibility = View.GONE
                }
                SearchFavouriteAuthorsViewModel.SearchStatus.FOUND -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.alertTextView.visibility = View.GONE
                    binding.confirmChoseButton.visibility = View.GONE
                }
            }
        })
    }

    private fun setAllClickListeners() {
        binding.confirmChoseButton.setOnClickListener {
            hideKeyboard()
            findNavController().popBackStack()
        }
    }

    private fun hideKeyboard() {
        binding.searchView.hideKeyboard()
    }
}