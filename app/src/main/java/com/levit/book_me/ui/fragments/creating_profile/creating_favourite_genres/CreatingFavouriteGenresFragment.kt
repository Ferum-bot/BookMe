package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.GenreCloud
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
        configureLayout()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.genres.observe(viewLifecycleOwner) { genres ->
            val adaptiveGenres = genres.map { genre ->
                GenreCloud.getFrom(genre)
            }
            binding.cloudsView.setClouds(adaptiveGenres)
        }
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

    private fun configureLayout() {
        val notCheckedTextColor = getColor(R.color.black)
        val notCheckedCloudColor = getColor(R.color.light_grey)
        val checkedTextColor = getColor(R.color.white)
        val checkedCloudColor = getColor(R.color.cloud_checked_color)

        with(binding.cloudsView) {
            setNotCheckedTextColor(notCheckedTextColor)
            setNotCheckedCloudColor(notCheckedCloudColor)
            setCheckedTextColor(checkedTextColor)
            setCheckedCloudColor(checkedCloudColor)
        }
    }

    private fun navigateToChooseFavouriteAuthorsFragment() {
        val action = CreatingFavouriteGenresFragmentDirections
            .actionCreatingFavouriteGenresFragmentToCreatingFavouriteAuthorsFragment()
        findNavController().navigate(action)
    }
}