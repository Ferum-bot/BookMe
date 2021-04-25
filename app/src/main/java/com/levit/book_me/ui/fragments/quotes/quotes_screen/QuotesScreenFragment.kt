package com.levit.book_me.ui.fragments.quotes.quotes_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.databinding.FragmentQuotesScreenBinding
import com.levit.book_me.ui.base.QuotesBaseFragment
import com.levit.book_me.ui.fragments.quotes.recycler.OffsetQuotesItemDecorator
import com.levit.book_me.ui.fragments.quotes.recycler.QuotesAdapter
import com.levit.book_me.ui.fragments.quotes.utill.ProfileQuoteStorage

class QuotesScreenFragment: QuotesBaseFragment(R.layout.fragment_quotes_screen) {

    private val binding by viewBinding { FragmentQuotesScreenBinding.bind(it) }

    private val viewModel by viewModels<QuotesScreenViewModel> { quotesComponent.viewModelFactory() }

    private val args by navArgs<QuotesScreenFragmentArgs>()

    private lateinit var quotesAdapter: QuotesAdapter

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
        setAllClickListeners()
        setAllObservers()
        searchQuotes()
    }

    private fun initAdapter() {
        val listener = object: QuotesAdapter.QuoteStatusListener {

            override fun onQuoteStatusChanged(status: QuotesAdapter.QuoteStatuses, quote: GoQuote) {
                when(status) {
                    QuotesAdapter.QuoteStatuses.CHOSEN -> {
                        binding.chooseButton.visibility = View.VISIBLE
                        safeQuoteToProfile(quote)
                    }
                    QuotesAdapter.QuoteStatuses.NOT_CHOSEN -> {
                        binding.chooseButton.visibility = View.GONE
                        removeQuoteFromProfile()
                    }
                }
            }
        }

        val decorator = OffsetQuotesItemDecorator()
        quotesAdapter = QuotesAdapter(listener)
        with(binding.recyclerView) {
            adapter = quotesAdapter
            addItemDecoration(decorator)
        }
    }

    private fun setAllClickListeners() {
        binding.chooseButton.setOnClickListener {
            navigateToCausedScreen()
        }
    }

    private fun setAllObservers() {
        viewModel.currentStatus.observe(viewLifecycleOwner, Observer { status ->
            when(status) {
                QuotesScreenViewModel.Statuses.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorLabel.visibility = View.GONE
                    binding.chooseButton.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                QuotesScreenViewModel.Statuses.LOADED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLabel.visibility = View.GONE
                    binding.chooseButton.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                QuotesScreenViewModel.Statuses.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLabel.visibility = View.VISIBLE
                    binding.chooseButton.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLabel.visibility = View.VISIBLE
                    binding.chooseButton.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
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

        viewModel.quotes.observe(viewLifecycleOwner, Observer { quotes ->
            quotesAdapter.submitList(quotes)
        })
    }

    private fun searchQuotes() {
        val type = args.type
        val query = args.querySearch
        viewModel.searchQuotes(type, query)
        binding.label.text = query
    }

    private fun navigateToCausedScreen() {
        findNavController().popBackStack()
        findNavController().popBackStack()
        findNavController().popBackStack()
    }

    private fun safeQuoteToProfile(quote: GoQuote) {
        ProfileQuoteStorage.setQuote(quote)
    }

    private fun removeQuoteFromProfile() {
        ProfileQuoteStorage.removeQuote()
    }
}