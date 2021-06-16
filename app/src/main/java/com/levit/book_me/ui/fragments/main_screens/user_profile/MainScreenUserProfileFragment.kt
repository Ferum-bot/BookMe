package com.levit.book_me.ui.fragments.main_screens.user_profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.ui.custom_view.SmallGenreView
import com.levit.book_me.databinding.FragmentMainScreenProfileBinding
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.ui.base.BaseMainScreenFragment
import com.levit.book_me.ui.fragments.utills.BaseBooksAdapter
import com.levit.book_me.ui.fragments.utills.BaseBooksOffsetDecorator

class MainScreenUserProfileFragment
    : BaseMainScreenFragment<MainScreenUserProfileViewModel>(R.layout.fragment_main_screen_profile){

    private val binding by viewBinding { FragmentMainScreenProfileBinding.bind(it) }

    override val viewModel: MainScreenUserProfileViewModel by viewModels {
        mainScreenComponent.viewModelFactory()
    }

    private val favoriteBooksAdapter by lazy {
        BaseBooksAdapter(isCheckable = false)
    }

    private val wantToReadBooksAdapter by lazy {
        BaseBooksAdapter(isCheckable = false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainScreenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
        configureLayout()
        setAllClickListeners()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.profileModel.observe(viewLifecycleOwner) { profileModel ->
            val genres = profileModel.favouriteGenres
            val authors = profileModel.favouriteAuthors
            val favoriteBooks = profileModel.favouriteBooks
            val wantToReadBooks = profileModel.wantToReadBooks

            setUpGenres(genres)
            setUpAuthors(authors)
            setUpFavoriteBooks(favoriteBooks)
            setUpWantToReadBooks(wantToReadBooks)
            setUpBaseInformation(profileModel)
        }

        viewModel.currentStatus.observe(viewLifecycleOwner) { status ->
            when(status) {
                MainScreenUserProfileViewModel.Status.LOADING -> {
                    showAllViews(false)
                    binding.progressBar.visibility = View.VISIBLE
                }
                MainScreenUserProfileViewModel.Status.PROFILE_MODEL_FROM_CACHE -> {
                    showAllViews(true)
                    binding.progressBar.visibility = View.GONE
                    binding.errorLabel.visibility = View.GONE
                }
                MainScreenUserProfileViewModel.Status.PROFILE_MODEL_FROM_REMOTE -> {
                    showAllViews(true)
                    binding.progressBar.visibility = View.GONE
                    binding.errorLabel.visibility = View.GONE
                }
                MainScreenUserProfileViewModel.Status.NOTHING_TO_SHOW, null -> {

                }
            }
        }
    }

    private fun configureLayout() {
        val baseBooksDecorator = BaseBooksOffsetDecorator()

        binding.favoriteBooksRecycler.apply {
            addItemDecoration(baseBooksDecorator)
            adapter = favoriteBooksAdapter
        }
        binding.wantToReadBooksRecycler.apply {
            addItemDecoration(baseBooksDecorator)
            adapter = wantToReadBooksAdapter
        }
    }

    private fun setAllClickListeners() {

    }

    private fun setUpGenres(genres: List<Genre>) {
        genres.forEach { genre ->
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            val view = SmallGenreView(requireContext()).apply {
                this.layoutParams = layoutParams
                id = View.generateViewId()
                setGenre(genre)
            }

            binding.constraintLayout.addView(view)
            binding.genresFlow.addView(view)
        }
    }

    private fun setUpAuthors(authors: List<Author>) {

    }

    private fun setUpFavoriteBooks(books: List<GoogleBook>) {
        favoriteBooksAdapter.submitList(books)
    }

    private fun setUpWantToReadBooks(books: List<GoogleBook>) {
        wantToReadBooksAdapter.submitList(books)
    }

    private fun setUpBaseInformation(profileModel: ProfileModel) {
        val name = profileModel.name
        val surname = profileModel.surname
        val wordsAboutPerson = profileModel.wordsAboutPerson
        val quote = profileModel.quote

        binding.nameSurnameTextView.text = "$name $surname"
        binding.wordsAboutPerson.text = wordsAboutPerson
        binding.profilePhoto.setImageResource(R.drawable.ic_user)
        binding.quote.hideAuthor(false)
        binding.quote.setChosen(true)
        binding.quote.setQuote(quote)
    }

    private fun showAllViews(show: Boolean) {
        binding.constraintLayout.forEach { view ->
            view.isVisible = show
        }
    }
}