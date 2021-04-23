package com.levit.book_me.ui.fragments.creating_profile.search_favourites_authors

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.R
import com.levit.book_me.core.extensions.hideKeyboard
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.databinding.FragmentSearchFavouriteAuthorsBinding
import com.levit.book_me.di.components.SearchFavouriteAuthorsComponent
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.FavouriteAuthorsStorage

class SearchFavouriteAuthorsFragment:
    BaseCreatingProfileFragment(R.layout.fragment_search_favourite_authors),
    SearchFavouriteAuthorsAdapter.AuthorStateListener {

    private lateinit var searchFavouriteAuthorsComponent: SearchFavouriteAuthorsComponent

    private val binding by viewBinding { FragmentSearchFavouriteAuthorsBinding.bind(it) }

    private val viewModel by viewModels<SearchFavouriteAuthorsViewModel> { searchFavouriteAuthorsComponent.viewModelFactory() }

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
        FavouriteAuthorsStorage.setAuthorTo(author, authorPosition)
    }

    private fun removeChosenAuthor() {
        FavouriteAuthorsStorage.removeAuthorFrom(authorPosition)
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

    private fun setAllObservers() {
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

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message != null) {
                hideKeyboard()
                showError(message)
                viewModel.errorMessageHasShown()
            }
        })

        viewModel.errorMessageId.observe(viewLifecycleOwner, Observer { messageId ->
            if (messageId != null) {
                hideKeyboard()
                showError(messageId)
                viewModel.errorMessageHasShown()
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