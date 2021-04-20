package com.levit.book_me.ui.fragments.creating_profile.search_favourite_authors

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.databinding.FragmentSearchFavouriteAuthorsBinding
import com.levit.book_me.ui.base.BaseCreatingProfileFragment

class SearchFavouriteAuthorsFragment: BaseCreatingProfileFragment(R.layout.fragment_search_favourite_authors) {

    private val binding by viewBinding { FragmentSearchFavouriteAuthorsBinding.bind(it) }

    private val viewModel by viewModels<SearchFavouriteAuthorsViewModel> { creatingProfileComponent.viewModelFactory() }

    private val adapter by lazy { SearchFavouriteAuthorsAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setSearchTextChangeListener()
        setAllObservers()
    }

    private fun initRecyclerView() {
        with(binding.recyclerView) {
            adapter = this@SearchFavouriteAuthorsFragment.adapter
            val context = requireContext()
            val decorator = DividerItemDecoration(context, RecyclerView.VERTICAL)
            decorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.search_favoutire_authors_item_decorator)!!)
            addItemDecoration(decorator)
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
                }
                SearchFavouriteAuthorsViewModel.SearchStatus.SEARCHING -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.alertTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                SearchFavouriteAuthorsViewModel.SearchStatus.NOTHING_FOUND -> {
                    binding.recyclerView.visibility = View.GONE
                    binding.alertTextView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                }
                SearchFavouriteAuthorsViewModel.SearchStatus.FOUND -> {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.alertTextView.visibility = View.GONE
                }
            }
        })

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
}