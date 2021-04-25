package com.levit.book_me.ui.fragments.quotes.authors_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.GoQuotesAuthor
import com.levit.book_me.core.models.GoQuotesTypes
import com.levit.book_me.databinding.FragmentQuotesAuthorsScreenBinding
import com.levit.book_me.ui.base.QuotesBaseFragment
import com.levit.book_me.ui.fragments.quotes.recycler.OffsetQuotesItemDecorator
import com.levit.book_me.ui.fragments.quotes.recycler.QuotesAuthorAdapter

class QuotesAuthorsScreenFragment: QuotesBaseFragment(R.layout.fragment_quotes_authors_screen) {

    private val binding by viewBinding { FragmentQuotesAuthorsScreenBinding.bind(it) }

    private val viewModel by viewModels<QuotesAuthorsScreenViewModel> { quotesComponent.viewModelFactory() }

    private lateinit var authorsAdapter: QuotesAuthorAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initDi()
    }

    override fun initDi() {
        super.initDi()

        quotesComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setAllObservers()
    }

    private fun initAdapter() {
        val listener = object: QuotesAuthorAdapter.AuthorClickListener {

            override fun onAuthorClicked(author: GoQuotesAuthor) {
                navigateToQuotesScreen(author)
            }
        }

        val decorator = OffsetQuotesItemDecorator()
        authorsAdapter = QuotesAuthorAdapter(listener)
        with(binding.recyclerView) {
            adapter = authorsAdapter
            addItemDecoration(decorator)
        }
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

        viewModel.currentStatus.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                QuotesAuthorsScreenViewModel.Statuses.LOADING -> {
                    binding.errorLabel.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                QuotesAuthorsScreenViewModel.Statuses.LOADED -> {
                    binding.errorLabel.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                QuotesAuthorsScreenViewModel.Statuses.ERROR -> {
                    binding.errorLabel.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                else -> {
                    binding.errorLabel.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        })

        viewModel.authors.observe(viewLifecycleOwner, Observer { authors ->
            authorsAdapter.submitList(authors)
        })
    }

    private fun navigateToQuotesScreen(author: GoQuotesAuthor) {
        val action = QuotesAuthorsScreenFragmentDirections
            .actionQuotesAuthorsScreenFragmentToQuotesScreenFragment(
                type = GoQuotesTypes.AUTHOR,
                querySearch = author.fullName
            )
        findNavController().navigate(action)
    }
}