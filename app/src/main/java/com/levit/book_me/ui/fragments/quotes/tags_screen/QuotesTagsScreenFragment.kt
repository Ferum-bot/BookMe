package com.levit.book_me.ui.fragments.quotes.tags_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.GoQuotesTag
import com.levit.book_me.core.models.GoQuotesTypes
import com.levit.book_me.databinding.FragmentQuotesTagsScreenBinding
import com.levit.book_me.ui.base.QuotesBaseFragment
import com.levit.book_me.ui.fragments.quotes.recycler.OffsetQuotesItemDecorator
import com.levit.book_me.ui.fragments.quotes.recycler.QuotesTagAdapter

class QuotesTagsScreenFragment: QuotesBaseFragment(R.layout.fragment_quotes_tags_screen) {

    private val binding by viewBinding { FragmentQuotesTagsScreenBinding.bind(it) }

    private val viewModel by viewModels<QuotesTagsScreenViewModel> { quotesComponent.viewModelFactory() }

    private lateinit var tagsAdapter: QuotesTagAdapter

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

        setUpAdapter()
        setAllObservers()
    }

    private fun setUpAdapter() {
        val listener = object: QuotesTagAdapter.TagClickListener {

            override fun onTagClicked(tag: GoQuotesTag) {
                navigateToQuotesScreen(tag)
            }
        }

        val decorator = OffsetQuotesItemDecorator()
        tagsAdapter = QuotesTagAdapter(listener)
        with(binding.recyclerView) {
            adapter = tagsAdapter
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
            when(status) {
                QuotesTagsScreenViewModel.Statuses.LOADING -> {
                    binding.errorLabel.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                QuotesTagsScreenViewModel.Statuses.LOADED -> {
                    binding.errorLabel.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
                QuotesTagsScreenViewModel.Statuses.ERROR -> {
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
    }

    private fun navigateToQuotesScreen(tag: GoQuotesTag) {
        val action = QuotesTagsScreenFragmentDirections
            .actionQuotesTagsScreenFragmentToQuotesScreenFragment(
                type = GoQuotesTypes.TAG,
                querySearch = tag.name,
            )
        findNavController().navigate(action)
    }
}