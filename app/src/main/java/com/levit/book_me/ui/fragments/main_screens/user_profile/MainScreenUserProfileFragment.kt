package com.levit.book_me.ui.fragments.main_screens.user_profile

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.defaultGlideOptions
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.ui.custom_view.SimpleAuthorView
import com.levit.book_me.core.ui.custom_view.SmallGenreView
import com.levit.book_me.core.utill.FirebaseStorageReferences
import com.levit.book_me.core.utill.ProfileImagePicker
import com.levit.book_me.core.utill.RemoteImageLoader
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

    private val profilePhotoLoader by lazy {
        val imageView = binding.profilePhoto
        val options = imageView.defaultGlideOptions()
        RemoteImageLoader(imageView, options)
    }

    private val photoPicker by lazy {
        ProfileImagePicker(activityResultRegistry, this, this::onImagePicked)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainScreenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
        configureLayout()
        initImagePicker()
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
                    showAllViews(false)
                    binding.progressBar.visibility = View.VISIBLE
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

        binding.errorLabel.addClickableText(R.string.try_again) {
            viewModel.getProfile()
        }
    }

    private fun initImagePicker() {
        photoPicker
    }

    private fun setAllClickListeners() {
        binding.changePhotoButton.setOnClickListener {
            photoPicker.pickPicture()
        }
    }

    private fun setUpGenres(genres: List<Genre>) {
        genres.forEach { genre ->
            val view = SmallGenreView.getWithBaseParams(this::requireContext)
            view.setGenre(genre)

            binding.constraintLayout.addView(view)
            binding.genresFlow.addView(view)
        }
    }

    private fun setUpAuthors(authors: List<Author>) {
        authors.forEach { author ->
            val authorView = SimpleAuthorView.getWithBaseParams(this::requireContext)
            authorView.setAuthor(author)

            binding.constraintLayout.addView(authorView)
            binding.authorFlow.addView(authorView)
        }
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
        val profilePhotoUrl = FirebaseStorageReferences.getStorageProfileImageReference()
        val quote = profileModel.quote

        binding.nameSurnameTextView.text = "$name $surname"
        binding.wordsAboutPerson.text = wordsAboutPerson
        binding.profilePhoto.setImageResource(R.drawable.ic_user)
        binding.quote.hideAuthor(false)
        binding.quote.setChosen(true)
        binding.quote.setQuote(quote)

        profilePhotoLoader.load(profilePhotoUrl)
    }

    private fun showAllViews(show: Boolean) {
        binding.constraintLayout.forEach { view ->
            view.isVisible = show
        }
    }

    private fun onImagePicked(uri: Uri?) {
        uri ?: return
        viewModel.uploadNewProfilePhoto(uri)
    }
}