package com.levit.book_me.ui.fragments.quotes.main_screen

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.core.ui.custom_view.QuoteTypeChooseView
import com.levit.book_me.databinding.FragmentQuotesMainScreenBinding
import com.levit.book_me.ui.base.QuotesBaseFragment
import com.levit.book_me.ui.fragments.quotes.recycler.QuotesAdapter

class QuotesMainScreenFragment: QuotesBaseFragment(R.layout.fragment_quotes_main_screen){

    private val binding by viewBinding { FragmentQuotesMainScreenBinding.bind(it) }

    private val viewModel by viewModels<QuotesMainScreenViewModel> { quotesComponent.viewModelFactory() }

    private lateinit var randomQuotesAdapter: QuotesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        initDi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun setUpAdapter() {
        val statusListener = object: QuotesAdapter.QuoteStatusListener {

            override fun onQuoteStatusChanged(status: QuotesAdapter.QuoteStatuses, quote: GoQuote) = when(status) {
                QuotesAdapter.QuoteStatuses.CHOSEN -> {
                    binding.chooseButton.visibility = View.VISIBLE
                }
                QuotesAdapter.QuoteStatuses.NOT_CHOSEN -> {
                    binding.chooseButton.visibility = View.GONE
                }
            }
        }

        val decorator = object: RecyclerView.ItemDecoration() {

            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = 16
            }
        }

        randomQuotesAdapter = QuotesAdapter(statusListener)
        with(binding.quoteRecyclerView) {
            adapter = randomQuotesAdapter
            addItemDecoration(decorator)
        }
    }

    private fun setAllClickListeners() {
        binding.chooseButton.setOnClickListener {

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
        val typeListener = object: QuoteTypeChooseView.OnTypeClickListener {

            override fun onTagsClicked() {
                navigateToTagsScreen()
            }

            override fun onAuthorsClicked() {
                navigateToAuthorsScreen()
            }
        }
        binding.quoteTypeChoose.setTypeListener(typeListener)
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
}