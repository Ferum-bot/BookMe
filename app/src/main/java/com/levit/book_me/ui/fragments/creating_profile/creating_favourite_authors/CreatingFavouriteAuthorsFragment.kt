package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_authors

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.ui.custom_view.CreatingProfileAuthorChooser
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.FragmentCreatingFavouriteAuthorsBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment

class CreatingFavouriteAuthorsFragment: BaseCreatingProfileFragment(R.layout.fragment_creating_favourite_authors) {

    companion object {
        private const val FRAGMENT_POSITION = 4
    }

    private val binding by viewBinding { FragmentCreatingFavouriteAuthorsBinding.bind(it) }

    private val viewModel by viewModels<CreatingFavouriteAuthorsViewModel> { creatingProfileComponent.viewModelFactory() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showMainTitle(true)
        updatePageIndicator()
        setUpAuthorChooser()
        setAllClickListeners()
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun setUpAuthorChooser() {
        val authorChooserListener = object: CreatingProfileAuthorChooser.AuthorChangeListener {

            override fun onAuthorAdd(authorPosition: Int) {
                showMainTitle(false)
                navigateToSearchAuthorFragment(authorPosition)
            }

            override fun onAuthorRemoved(authorPosition: Int, author: Author) {

            }
        }

        binding.authorChooser.setAuthorChangeListener(authorChooserListener)
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            navigateToCreatingFavouriteBooksFragment()
        }
    }

    private fun navigateToSearchAuthorFragment(authorPosition: Int) {
        val action = CreatingFavouriteAuthorsFragmentDirections
            .actionCreatingFavouriteAuthorsFragmentToSearchFavouriteAuthorsFragment()
        findNavController().navigate(action)
    }

    private fun navigateToCreatingFavouriteBooksFragment() {
        val action = CreatingFavouriteAuthorsFragmentDirections
            .actionCreatingFavouriteAuthorsFragmentToCreatingFavouriteBooksFragment()
        findNavController().navigate(action)
    }
}