package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres

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

class CreatingFavouriteGenresFragment: BaseCreatingProfileFragment(R.layout.fragment_creating_favourite_genres) {

    companion object {
        private const val FRAGMENT_POSITION = 3
    }

    private val binding by viewBinding { FragmentCreatingFavouriteGenresBinding.bind(it) }

    private val viewModel by viewModels<CreatingFavouriteGenresViewModel> { creatingProfileComponent.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllClickListeners()
        setAllObservers()
        updatePageIndicator()
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            navigateToChooseFavouriteAuthorsFragment()
        }
    }

    private fun setAllObservers() {

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