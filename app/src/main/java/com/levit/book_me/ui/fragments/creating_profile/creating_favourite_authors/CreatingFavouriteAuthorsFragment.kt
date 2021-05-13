package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_authors

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.ui.custom_view.CreatingProfileAuthorChooser
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.databinding.FragmentCreatingFavouriteAuthorsBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.FavouriteAuthorsStorage
import kotlinx.coroutines.GlobalScope

class CreatingFavouriteAuthorsFragment:
    BaseCreatingProfileFragment<CreatingFavouriteAuthorsViewModel>(R.layout.fragment_creating_favourite_authors) {

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

        showMainTitle(true)
        updatePageIndicator()
        setUpAuthorChooser()
        setAllClickListeners()
        setAllObservers()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        FavouriteAuthorsStorage.firstAuthor.observe(viewLifecycleOwner, Observer { author ->
            if (author != null) {
                binding.authorChooser.setAuthor(CreatingProfileAuthorChooser.AuthorPosition.FIRST_POSITION, author)
            }
        })

        FavouriteAuthorsStorage.secondAuthor.observe(viewLifecycleOwner, Observer { author ->
            if (author != null) {
                binding.authorChooser.setAuthor(CreatingProfileAuthorChooser.AuthorPosition.SECOND_POSITION, author)
            }
        })

        FavouriteAuthorsStorage.thirdAuthor.observe(viewLifecycleOwner, Observer { author ->
            if (author != null) {
                binding.authorChooser.setAuthor(CreatingProfileAuthorChooser.AuthorPosition.THIRD_POSITION, author)
            }
        })

        FavouriteAuthorsStorage.foursAuthor.observe(viewLifecycleOwner, Observer { author ->
            if (author != null) {
                binding.authorChooser.setAuthor(CreatingProfileAuthorChooser.AuthorPosition.FOURS_POSITION, author)
            }
        })

        FavouriteAuthorsStorage.fivesAuthor.observe(viewLifecycleOwner, Observer { author ->
            if (author != null) {
                binding.authorChooser.setAuthor(CreatingProfileAuthorChooser.AuthorPosition.FIVES_POSITION, author)
            }
        })
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun setUpAuthorChooser() {
        val authorChooserListener = object: CreatingProfileAuthorChooser.AuthorChangeListener {

            override fun onAuthorAdd(authorPosition: CreatingProfileAuthorChooser.AuthorPosition) {
                showMainTitle(false)
                navigateToSearchAuthorFragment(authorPosition)
            }

            override fun onAuthorRemoved(authorPosition: CreatingProfileAuthorChooser.AuthorPosition, author: Author) {
                FavouriteAuthorsStorage.removeAuthorFrom(authorPosition)
            }
        }

        binding.authorChooser.setAuthorChangeListener(authorChooserListener)
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