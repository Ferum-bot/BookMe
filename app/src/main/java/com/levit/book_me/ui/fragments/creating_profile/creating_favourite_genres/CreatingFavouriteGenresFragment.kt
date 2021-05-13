package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentCreatingFavouriteGenresBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment

class CreatingFavouriteGenresFragment:
    BaseCreatingProfileFragment<CreatingFavouriteGenresViewModel>(R.layout.fragment_creating_favourite_genres) {

    companion object {
        private const val FRAGMENT_POSITION = 3
    }

    override val viewModel by viewModels<CreatingFavouriteGenresViewModel> { creatingProfileComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentCreatingFavouriteGenresBinding.bind(it) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllClickListeners()
        setAllObservers()
        updatePageIndicator()
    }

    override fun setAllObservers() {
        super.setAllObservers()

    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            navigateToChooseFavouriteAuthorsFragment()
        }
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun navigateToChooseFavouriteAuthorsFragment() {
        val action = CreatingFavouriteGenresFragmentDirections
            .actionCreatingFavouriteGenresFragmentToCreatingFavouriteAuthorsFragment()
        findNavController().navigate(action)
    }
}