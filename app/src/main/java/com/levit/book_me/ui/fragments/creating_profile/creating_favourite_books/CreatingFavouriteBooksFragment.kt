package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_books

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentCreatingFavouriteBooksBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment

class CreatingFavouriteBooksFragment: BaseCreatingProfileFragment(R.layout.fragment_creating_favourite_books) {

    companion object {
        private const val FRAGMENT_POSITION = 5
    }

    private val binding by viewBinding { FragmentCreatingFavouriteBooksBinding.bind(it) }

    private val viewModel by viewModels<CreatingFavouriteBooksViewModel> { creatingProfileComponent.viewModelFactory() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updatePageIndicator()
        configureAllViews()
        setAllObservers()
        setAllClickListeners()
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun configureAllViews() {
        binding.searchView.isEnabled = false
    }

    private fun setAllObservers() {

    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {

        }

        binding.searchView.setOnClickListener {

        }
    }
}