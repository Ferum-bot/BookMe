package com.levit.book_me.ui.fragments.quotes.authors_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.hideKeyboard
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.quote.GoQuotesAuthor
import com.levit.book_me.core.models.GoQuotesTypes
import com.levit.book_me.databinding.FragmentQuotesAuthorsScreenBinding
import com.levit.book_me.ui.base.QuotesBaseFragment
import com.levit.book_me.ui.fragments.quotes.recycler.OffsetQuotesItemDecorator
import com.levit.book_me.ui.fragments.quotes.recycler.QuotesAuthorAdapter

class QuotesAuthorsScreenFragment:
    QuotesBaseFragment(R.layout.fragment_quotes_authors_screen),
    QuotesAuthorAdapter.AuthorClickListener,
    SearchView.OnQueryTextListener {

    private val binding by viewBinding { FragmentQuotesAuthorsScreenBinding.bind(it) }

    private val viewModel by viewModels<QuotesAuthorsScreenViewModel> { quotesComponent.viewModelFactory() }

    private val authorsAdapter by lazy { QuotesAuthorAdapter(this) }

    private val searchView: SearchView?
    get() = binding.toolBar.menu
        .findItem(R.id.menu_search_item).actionView as? SearchView

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

        configureLayout()
        initAdapter()
        setAllObservers()
    }

    override fun onAuthorClicked(author: GoQuotesAuthor) {
        navigateToQuotesScreen(author)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.getAuthorsByQuery(newText)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchView?.isIconified = false
        searchView?.hideKeyboard()
        return true
    }

    private fun configureLayout() {
        binding.errorLabel.addClickableText(R.string.try_again) {
            viewModel.getAllAuthors()
        }

        configureSearchActionView()
    }

    private fun configureSearchActionView() {
        val searchActionView = searchView
        searchActionView ?: return

        searchActionView.queryHint = getString(R.string.search_by_authors)
        searchActionView.setOnQueryTextListener(this)
    }

    private fun initAdapter() {

        val decorator = OffsetQuotesItemDecorator()
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