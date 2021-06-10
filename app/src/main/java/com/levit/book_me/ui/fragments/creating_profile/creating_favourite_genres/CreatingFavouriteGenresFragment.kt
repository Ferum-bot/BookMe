package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Genre
import com.levit.book_me.databinding.FragmentCreatingFavouriteGenresBinding
import com.levit.book_me.roundcloudsview.core.enums.RoundCloudState
import com.levit.book_me.roundcloudsview.core.interfaces.RoundCloudStateChangeListener
import com.levit.book_me.roundcloudsview.core.models.RoundCloud
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants

class CreatingFavouriteGenresFragment:
    BaseCreatingProfileFragment<CreatingFavouriteGenresViewModel>(R.layout.fragment_creating_favourite_genres),
    RoundCloudStateChangeListener {

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

        viewModel.errorMessageId.removeObservers(viewLifecycleOwner)
        viewModel.errorMessageId.observe(viewLifecycleOwner) { messageId ->
            if (messageId == null) {
                return@observe
            }
            when (messageId) {
                R.string.choose_from_to_genres -> {
                    val errorString = getString(
                        messageId,
                        CreatingProfileConstants.MIN_COUNT_CHOSEN_GENRES.toString(),
                        CreatingProfileConstants.MAX_COUNT_CHOSEN_GENRES.toString(),
                    )
                    showError(errorString)
                }
                else -> {
                    showError(messageId)
                }
            }
            viewModel.errorMessageHasShown()
        }

        viewModel.genres.observe(viewLifecycleOwner) { genres ->
            binding.cloudsView.setClouds(genres)
        }

        viewModel.genresAreChosen.observe(viewLifecycleOwner) { genresAreChosen ->
            binding.errorLabel.isVisible = !genresAreChosen
        }
    }

    override fun onStateChanged(newState: RoundCloudState, cloud: RoundCloud) {
        val genre = (cloud as? Genre) ?: return
        when (newState) {
            RoundCloudState.CHECKED ->
                viewModel.addGenre(genre)
            RoundCloudState.NOT_CHECKED ->
                viewModel.removeGenre(genre)
        }
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            if (viewModel.allGenresAreChosen()) {
                val chosenGenres = viewModel.genres.value ?: emptyList()
                sharedViewModel.safeChosenGenres(chosenGenres)
                navigateToChooseFavouriteAuthorsFragment()
            }
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
            setCloudStateChangeListener(this@CreatingFavouriteGenresFragment)
        }

        val labelDescription = getString(
            R.string.choose_from_to_genres,
            CreatingProfileConstants.MIN_COUNT_CHOSEN_GENRES.toString(),
            CreatingProfileConstants.MAX_COUNT_CHOSEN_GENRES.toString(),
        )
        val errorDescription = getString(
            R.string.chose_at_least_genres,
            CreatingProfileConstants.MIN_COUNT_CHOSEN_GENRES.toString(),
        )
        binding.labelDescription.text = labelDescription
        binding.errorLabel.text = errorDescription
    }

    private fun navigateToChooseFavouriteAuthorsFragment() {
        val action = CreatingFavouriteGenresFragmentDirections
            .actionCreatingFavouriteGenresFragmentToCreatingFavouriteAuthorsFragment()
        findNavController().navigate(action)
    }
}