package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_authors

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.ui.custom_view.CreatingProfileAuthorChooser
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.FragmentCreatingFavouriteAuthorsBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants

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
        configureLayout()
        setUpAuthorChooser()
        setAllClickListeners()
        setAllObservers()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        sharedViewModel.chosenFavouriteAuthors.observe(viewLifecycleOwner,  { chosenAuthors ->
            val authors = mutableListOf<Author>()

            chosenAuthors.forEach { authorPair ->
                authors.add(authorPair.first)
                binding.authorChooser.setAuthor(
                    author = authorPair.first,
                    position = authorPair.second,
                )
            }
            viewModel.setChosenAuthors(authors)
        })

        viewModel.isChosenEnoughAuthors.observe(viewLifecycleOwner, { isChosenEnoughAuthors ->
            binding.errorLabel.isVisible = !isChosenEnoughAuthors
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

    private fun configureLayout() {
        val labelDescription = getString(
            R.string.choose_from_to_authors,
            CreatingProfileConstants.MIN_COUNT_OF_FAVOURITE_AUTHORS.toString(),
            CreatingProfileConstants.MAX_COUNT_OF_FAVOURITE_AUTHORS.toString(),
        )
        val errorLabel = getString(
            R.string.choose_at_least_authors,
            CreatingProfileConstants.MIN_COUNT_OF_FAVOURITE_AUTHORS.toString()
        )

        binding.labelDescription.text = labelDescription
        binding.errorLabel.text = errorLabel
    }

    private fun setUpAuthorChooser() {
        binding.authorChooser.setAuthorChangeListener(this)
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            if (viewModel.everythingIsValid()) {
                navigateToCreatingFavouriteBooksFragment()
            }
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