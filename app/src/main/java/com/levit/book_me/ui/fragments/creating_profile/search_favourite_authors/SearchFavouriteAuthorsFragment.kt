package com.levit.book_me.ui.fragments.creating_profile.search_favourite_authors

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.databinding.FragmentSearchFavouriteAuthorsBinding
import com.levit.book_me.ui.base.BaseCreatingProfileFragment

class SearchFavouriteAuthorsFragment: BaseCreatingProfileFragment(R.layout.fragment_search_favourite_authors) {

    private val binding by viewBinding { FragmentSearchFavouriteAuthorsBinding.bind(it) }

    private val viewModel by viewModels<SearchFavouriteAuthorsViewModel> { creatingProfileComponent.viewModelFactory() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSearchTextChangeListener()
        setAllObservers()
    }

    private fun setSearchTextChangeListener() {
        binding.searchView.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = viewModel::onSearchTextChanged
        })
    }

    private fun setAllObservers() {
        viewModel.searchResult.observe(viewLifecycleOwner, Observer { listOfAuthors ->
            if (listOfAuthors == null) {
                binding.alertTextView.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
            else {
                setAuthorsToAdapter(listOfAuthors)
            }
        })
    }

    private fun setAuthorsToAdapter(listOfAuthors: List<Author>) {
        if (listOfAuthors.isEmpty()) {
            binding.alertTextView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            return
        }
    }
}