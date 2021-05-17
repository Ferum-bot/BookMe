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

class CreatingFavouriteAuthorsFragment:
    BaseCreatingProfileFragment<CreatingFavouriteAuthorsViewModel>(R.layout.fragment_creating_favourite_authors),
    CreatingProfileAuthorChooser.AuthorChangeListener{

    companion object {
        private const val FRAGMENT_POSITION = 4
    }

    override val viewModel by viewModels<CreatingFavouriteAuthorsViewModel> { creatingProfileComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentCreatingFavouriteAuthorsBinding.bind(it) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showMainPageIndicator(true)
        updatePageIndicator()
        setUpAuthorChooser()
        setAllClickListeners()
        setAllObservers()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        sharedViewModel.chosenFavouriteAuthors.observe(viewLifecycleOwner,  { chosenAuthors ->
            chosenAuthors.forEach { authorPair ->
                binding.authorChooser.setAuthor(
                    author = authorPair.first,
                    position = authorPair.second,
                )
            }
        })
    }

    override fun onAuthorAdd(authorPosition: CreatingProfileAuthorChooser.AuthorPosition) {
        showMainPageIndicator(false)
        navigateToSearchAuthorFragment(authorPosition)
    }

    override fun onAuthorRemoved(
        authorPosition: CreatingProfileAuthorChooser.AuthorPosition,
        author: Author
    ) {
        sharedViewModel.removeFavouriteAuthor(authorPosition)
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun setUpAuthorChooser() {
        binding.authorChooser.setAuthorChangeListener(this)
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            navigateToCreatingFavouriteBooksFragment()
        }
    }

    private fun navigateToSearchAuthorFragment(authorPosition: CreatingProfileAuthorChooser.AuthorPosition) {
        val action = CreatingFavouriteAuthorsFragmentDirections
            .actionCreatingFavouriteAuthorsFragmentToSearchFavouriteAuthorsFragment(authorPosition)
        findNavController().navigate(action)
    }

    private fun navigateToCreatingFavouriteBooksFragment() {
        val action = CreatingFavouriteAuthorsFragmentDirections
            .actionCreatingFavouriteAuthorsFragmentToCreatingFavouriteBooksFragment()
        findNavController().navigate(action)
    }
}