package com.levit.book_me.ui.fragments.quotes.main_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.ui.custom_view.QuoteTypeChooseView
import com.levit.book_me.databinding.FragmentQuotesMainScreenBinding
import com.levit.book_me.ui.base.QuotesBaseFragment
import com.levit.book_me.ui.fragments.quotes.recycler.OffsetQuotesItemDecorator
import com.levit.book_me.ui.fragments.quotes.recycler.QuotesAdapter
import com.levit.book_me.ui.fragments.quotes.utill.ProfileQuoteStorage

class QuotesMainScreenFragment:
    QuotesBaseFragment(R.layout.fragment_quotes_main_screen),
    QuotesAdapter.QuoteStatusListener,
    QuoteTypeChooseView.OnTypeClickListener {

    private val binding by viewBinding { FragmentQuotesMainScreenBinding.bind(it) }

    private val viewModel by viewModels<QuotesMainScreenViewModel> { quotesComponent.viewModelFactory() }

    private val randomQuotesAdapter by lazy { QuotesAdapter(this) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initDi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureLayout()
        setUpAdapter()
        hideCreatingProfileTitle()
        setAllClickListeners()
        setAllObservers()
        setTypeListener()
    }

    override fun initDi() {
        super.initDi()

        quotesComponent.inject(this)
    }

    override fun onQuoteStatusChanged(status: QuotesAdapter.QuoteStatuses, quote: QuoteModel) {
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

    override fun onAuthorsClicked() {
        navigateToAuthorsScreen()
    }

    override fun onTagsClicked() {
        navigateToTagsScreen()
    }

    private fun configureLayout() {
        binding.errorLabel.addClickableText(R.string.try_again) {
            viewModel.launchGettingScreenModel()
        }
    }

    private fun setUpAdapter() {
        val decorator = OffsetQuotesItemDecorator()
        with(binding.quoteRecyclerView) {
            adapter = randomQuotesAdapter
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
                QuotesMainScreenViewModel.QuotesMainScreenStatuses.LOADED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLabel.visibility = View.GONE
                    binding.chooseButton.visibility = View.GONE
                    binding.quoteRecyclerView.visibility = View.VISIBLE
                    binding.quoteTypeChoose.visibility = View.VISIBLE
                    binding.randomQuotesLabel.visibility = View.VISIBLE
                }
                QuotesMainScreenViewModel.QuotesMainScreenStatuses.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorLabel.visibility = View.GONE
                    binding.chooseButton.visibility = View.GONE
                    binding.quoteRecyclerView.visibility = View.GONE
                    binding.randomQuotesLabel.visibility = View.GONE
                    binding.quoteTypeChoose.visibility = View.GONE
                }
                QuotesMainScreenViewModel.QuotesMainScreenStatuses.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLabel.visibility = View.VISIBLE
                    binding.chooseButton.visibility = View.GONE
                    binding.quoteRecyclerView.visibility = View.GONE
                    binding.quoteTypeChoose.visibility = View.GONE
                    binding.randomQuotesLabel.visibility = View.GONE
                }
                else -> {
                    binding.progressBar.visibility = View.GONE
                    binding.errorLabel.visibility = View.VISIBLE
                    binding.chooseButton.visibility = View.GONE
                    binding.quoteRecyclerView.visibility = View.GONE
                    binding.quoteTypeChoose.visibility = View.GONE
                    binding.randomQuotesLabel.visibility = View.GONE
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

        viewModel.screenModel.observe(viewLifecycleOwner, Observer { model ->
            if (model != null) {
                val authors = model.numberOfAuthors
                val tags = model.numberOfTags
                val quotes = model.randomQuotes

                binding.quoteTypeChoose.setNumberOfTags(tags)
                binding.quoteTypeChoose.setNumberOfAuthors(authors)
                randomQuotesAdapter.submitList(quotes)
            }
        })
    }

    private fun setTypeListener() {
        binding.quoteTypeChoose.setTypeListener(this)
    }

    private fun navigateToAuthorsScreen() {
        val action = QuotesMainScreenFragmentDirections
            .actionQuotesMainScreenFragmentToQuotesAuthorsScreenFragment()
        findNavController().navigate(action)
    }

    private fun navigateToTagsScreen() {
        val action = QuotesMainScreenFragmentDirections
            .actionQuotesMainScreenFragmentToQuotesTagsScreenFragment()
        findNavController().navigate(action)
    }

    private fun navigateToCausedScreen() {
        findNavController().popBackStack()
    }

    private fun safeQuoteToProfile(quote: QuoteModel) {
        ProfileQuoteStorage.setQuote(quote)
    }

    private fun removeQuoteFromProfile() {
        ProfileQuoteStorage.removeQuote()
    }
}