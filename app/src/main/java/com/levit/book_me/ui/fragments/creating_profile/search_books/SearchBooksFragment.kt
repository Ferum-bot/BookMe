package com.levit.book_me.ui.fragments.creating_profile.search_books

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentSearchBooksBinding
import com.levit.book_me.di.components.SearchBooksComponent
import com.levit.book_me.ui.base.BaseCreatingProfileFragment

class SearchBooksFragment: BaseCreatingProfileFragment(R.layout.fragment_search_books) {

    private lateinit var searchBooksComponent: SearchBooksComponent

    private val binding by viewBinding { FragmentSearchBooksBinding.bind(it) }

    private val viewModel by viewModels<SearchBooksViewModel> { searchBooksComponent.viewModelFactory() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initDI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllClickListeners()
        setAllObservers()
    }

    private fun initDI() {
        creatingProfileComponent.inject(this)
        searchBooksComponent = creatingProfileComponent.searchBooksComponent()
            .build()
        searchBooksComponent.inject(this)
    }

    private fun setAllClickListeners() {

    }

    private fun setAllObservers() {

    }
}