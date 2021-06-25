package com.levit.book_me.ui.fragments.main_screens.current_friend_profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.forEach
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.levit.book_me.R
import com.levit.book_me.core.extensions.addClickableText
import com.levit.book_me.core.extensions.noCacheGlideOptions
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.ui.custom_view.SimpleAuthorView
import com.levit.book_me.core.ui.custom_view.SmallGenreView
import com.levit.book_me.core.utill.FirebaseStorageReferences
import com.levit.book_me.core.utill.FriendTimeLeftAdapter
import com.levit.book_me.core.utill.RemoteImageLoader
import com.levit.book_me.databinding.FragmentMainScreenCurrentFriendBinding
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.ui.base.BaseMainScreenFragment
import com.levit.book_me.ui.fragments.utills.BaseBooksAdapter
import com.levit.book_me.ui.fragments.utills.BaseBooksOffsetDecorator

class MainScreenFriendProfileFragment:
    BaseMainScreenFragment<MainScreenFriendProfileViewModel>(R.layout.fragment_main_screen_current_friend) {

    override val viewModel: MainScreenFriendProfileViewModel by viewModels {
        mainScreenComponent.viewModelFactory()
    }

    private val binding: FragmentMainScreenCurrentFriendBinding by viewBinding {
        FragmentMainScreenCurrentFriendBinding.bind(it)
    }

    private val favoriteBooksAdapter by lazy {
        BaseBooksAdapter(isCheckable = false)
    }

    private val wantToReadBooksAdapter by lazy {
        BaseBooksAdapter(isCheckable = false)
    }

    private val profilePhotoLoader by lazy {
        val view = binding.baseInformation.profilePhoto
        val options = view.noCacheGlideOptions()
        RemoteImageLoader(view, options)
    }

    private val currentAuthorViews = mutableListOf<SimpleAuthorView>()
    private val currentGenreViews = mutableListOf<SmallGenreView>()

    private var friendLeftTimer: FriendTimeLeftAdapter? = null

    var currentFriendId: Long = -1
    set(value) {
        field = value
        viewModel.loadCurrentFriend(field)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainScreenComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllObservers()
        initImagePicker()
        setAllClickListeners()
        configureLayout()
    }

    override fun setAllObservers() {
        super.setAllObservers()

        viewModel.currentFriend.observe(viewLifecycleOwner) { friendProfile ->
            val profile = friendProfile.model
            val launchTime = friendProfile.launchTimeUTC0
            val favoriteGenres = profile.favouriteGenres
            val favoriteAuthors = profile.favouriteAuthors
            val favoriteBooks = profile.favouriteBooks
            val wantToReadBooks = profile.wantToReadBooks

            setUpBaseInformation(profile)
            setUpAuthors(favoriteAuthors)
            setUpGenres(favoriteGenres)
            setUpFavoriteBooks(favoriteBooks)
            setUpWantToReadBooks(wantToReadBooks)
            setUpLeftTimer(launchTime)
        }

        viewModel.currentStatus.observe(viewLifecycleOwner) { status ->
            when(status) {
                MainScreenFriendProfileViewModel.Statuses.LOADING -> {
                    hideAllViews(true)
                    binding.baseInformation.progressBar.visibility = View.VISIBLE
                }
                MainScreenFriendProfileViewModel.Statuses.PROFILE_FROM_CACHE -> {
                    hideAllViews(false)
                }
                MainScreenFriendProfileViewModel.Statuses.PROFILE_FROM_REMOTE -> {
                    hideAllViews(false)
                }
                MainScreenFriendProfileViewModel.Statuses.USER_HAS_NO_FRIENDS -> {
                    hideAllViews(true)
                    showNoAvailableFriendsLabels()
                }
                MainScreenFriendProfileViewModel.Statuses.NO_AVAILABLE_DATA, null -> {
                    hideAllViews(true)
                    showNoDataLabels()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        friendLeftTimer?.cancel()
    }

    private fun setAllClickListeners() {
        binding.baseInformation.searchNewBookishFriend.setOnClickListener {
            sharedViewModel.searchNewFriend()
        }
    }

    private fun configureLayout() {
        val decorator = BaseBooksOffsetDecorator()

        binding.baseInformation.errorLabel.addClickableText(R.string.try_again) {
            viewModel.loadCurrentFriend(currentFriendId)
        }
        binding.baseInformation.wantToReadBooksRecycler.apply {
            adapter = wantToReadBooksAdapter
            addItemDecoration(decorator)
        }
        binding.baseInformation.favoriteBooksRecycler.apply {
            adapter = favoriteBooksAdapter
            addItemDecoration(decorator)
        }

        disableEditButtons()
    }

    private fun initImagePicker() {
        profilePhotoLoader
    }

    private fun disableEditButtons() {
        binding.baseInformation.changePhotoButton.isVisible = false
        binding.baseInformation.changePhotoButton.isClickable = false
        binding.baseInformation.quote.isClickable = false
    }

    private fun showNoAvailableFriendsLabels() {
        with(binding.baseInformation) {
            searchNewBookishFriend.isVisible = true
            haveNotFriendsLabel.isVisible = true
        }
    }

    private fun showNoDataLabels() {
        binding.baseInformation.errorLabel.isVisible = true
    }

    private fun hideAllViews(hide: Boolean) {
        binding.timeLeftLabel.isVisible = !hide
        binding.timeLeftView.isVisible = !hide

        with(binding.baseInformation) {
            profilePhoto.isVisible = !hide
            progressBar.isVisible = !hide
            errorLabel.isVisible = !hide
            haveNotFriendsLabel.isVisible = !hide
            searchNewBookishFriend.isVisible = !hide
            quote.isVisible = !hide
            authorFlow.isVisible = !hide
            genresFlow.isVisible = !hide
            nameSurnameTextView.isVisible = !hide
            wordsAboutPerson.isVisible = !hide
            authorsLabel.isVisible = !hide
            favoriteBooksLabel.isVisible = !hide
            wantToReadBooksLabel.isVisible = !hide
            favoriteBooksHost.isVisible = !hide
            wantToReadBooksHost.isVisible = !hide
        }
    }

    private fun setUpGenres(genres: List<Genre>) {
        currentGenreViews.forEach { view ->
            binding.baseInformation.genresFlow.removeView(view)
            binding.baseInformation.constraintLayout.removeView(view)
        }.also { currentGenreViews.clear() }

        genres.forEach { genre ->
            val view = SmallGenreView.getWithBaseParams(this::requireContext)
            view.setGenre(genre)

            binding.baseInformation.constraintLayout.addView(view)
            binding.baseInformation.genresFlow.addView(view)
            currentGenreViews.add(view)
        }
    }

    private fun setUpAuthors(authors: List<Author>) {
        currentAuthorViews.forEach { view ->
            binding.baseInformation.authorFlow.removeView(view)
            binding.baseInformation.constraintLayout.removeView(view)
        }.also { currentAuthorViews.clear() }

        authors.forEach { author ->
            val authorView = SimpleAuthorView.getWithBaseParams(this::requireContext)
            authorView.setAuthor(author)

            binding.baseInformation.constraintLayout.addView(authorView)
            binding.baseInformation.authorFlow.addView(authorView)
            currentAuthorViews.add(authorView)
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

        with(binding.baseInformation) {
            nameSurnameTextView.text = "$name $surname"
            profilePhoto.setImageResource(R.drawable.ic_user)
            this.wordsAboutPerson.text = wordsAboutPerson
            this.quote.hideAuthor(false)
            this.quote.setChosen(true)
            this.quote.setQuote(quote)
        }

        profilePhotoLoader.load(profilePhotoUrl)
    }

    private fun setUpLeftTimer(launchTimeUTC0: String) {
        friendLeftTimer?.cancel()
        friendLeftTimer = FriendTimeLeftAdapter(
            timeView = binding.timeLeftView,
            friendLaunchTimeUTC0 = launchTimeUTC0,
            onTimerComplete = this::onFriendTimeLeft
        )
        friendLeftTimer?.start()
    }

    private fun onFriendTimeLeft() {

    }
}