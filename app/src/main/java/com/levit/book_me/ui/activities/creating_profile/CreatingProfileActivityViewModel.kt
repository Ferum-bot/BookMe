package com.levit.book_me.ui.activities.creating_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.enums.SearchBooksTypes
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core.ui.custom_view.CreatingProfileAuthorChooser
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.creating_profile.CreatingProfileInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class CreatingProfileActivityViewModel @Inject constructor(
    private val interactor: CreatingProfileInteractor,
): BaseViewModel() {

    enum class Status {
        LOADING, ERROR, DONE, NOTHING;
    }

    private val _chosenFavouriteAuthors:
        MutableLiveData<List<Pair<Author, CreatingProfileAuthorChooser.AuthorPosition>>> =
        MutableLiveData()
    val chosenFavouriteAuthors:
        LiveData<List<Pair<Author, CreatingProfileAuthorChooser.AuthorPosition>>> =
        _chosenFavouriteAuthors

    private val _chosenFavouriteBooks: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val chosenFavouriteBooks: LiveData<List<GoogleBook>> = _chosenFavouriteBooks

    private val _chosenWantToReadBooks: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val chosenWantToReadBooks: LiveData<List<GoogleBook>> = _chosenWantToReadBooks

    private val _chosenGenres: MutableLiveData<List<Genre>> = MutableLiveData()
    val chosenGenres: LiveData<List<Genre>> = _chosenGenres

    private val _name: MutableLiveData<String> = MutableLiveData()
    val name: LiveData<String> = _name

    private val _surname: MutableLiveData<String> = MutableLiveData()
    val surname: LiveData<String> = _surname

    private val _wordsAboutProfile: MutableLiveData<String> = MutableLiveData()
    val wordsAboutProfile: LiveData<String> = _wordsAboutProfile

    private val _quote: MutableLiveData<GoQuote> = MutableLiveData()
    val quote: LiveData<GoQuote> = _quote

    private val _profilePhotoUrl: MutableLiveData<String> = MutableLiveData()
    val profilePhotoUrl: LiveData<String> = _profilePhotoUrl

    private val _creatingProfileStatus: MutableLiveData<Status> = MutableLiveData()
    val creatingProfileStatus: LiveData<Status> = _creatingProfileStatus

    private var creatingProfileJob: Job? = null

    init {
        viewModelScope.launch {
            interactor.resultStatus.collect { result ->
                handleCreatingProfileResult(result)
            }
        }
    }

    fun safeBaseProfileInformation(
        name: String, surname: String,
        wordsAboutYou: String, quote: GoQuote
    ) {
        _name.postValue(name)
        _surname.postValue(surname)
        _wordsAboutProfile.postValue(wordsAboutYou)
        _quote.postValue(quote)
    }

    fun safeProfileImageUrl(profileUrl: String) {
        _profilePhotoUrl.postValue(profileUrl)
    }

    fun safeFavouriteAuthor(author: Author, position: CreatingProfileAuthorChooser.AuthorPosition) {
        val authors = _chosenFavouriteAuthors.value?.toMutableList()
            ?: mutableListOf()
        val authorPair = author to position
        authors.add(authorPair)
        _chosenFavouriteAuthors.postValue(authors)
    }

    fun removeFavouriteAuthor(position: CreatingProfileAuthorChooser.AuthorPosition) {
        var authors = _chosenFavouriteAuthors.value?.toMutableList()
            ?: mutableListOf()
        authors = authors.filter { authorPair ->
            authorPair.second != position
        }.toMutableList()
        _chosenFavouriteAuthors.postValue(authors)
    }

    fun addChosenBook(type: SearchBooksTypes, book: GoogleBook) {
        when(type) {
            SearchBooksTypes.FAVOURITE_BOOKS -> addFavouriteBook(book)
            SearchBooksTypes.BOOKS_YOU_WANT_TO_RED -> addWantToReadBook(book)
        }
    }

    fun removeChosenBook(type: SearchBooksTypes, book: GoogleBook) {
        when(type) {
            SearchBooksTypes.FAVOURITE_BOOKS -> removeFavouriteBook(book)
            SearchBooksTypes.BOOKS_YOU_WANT_TO_RED -> removeWantToReadBook(book)
        }
    }

    fun safeChosenGenres(genres: List<Genre>) {
        _chosenGenres.postValue(genres)
    }

    fun registerNewUser() {
        val profile = ProfileModel(
            name = name.value ?: "",
            surname = surname.value ?: "",
            wordsAboutPerson = wordsAboutProfile.value ?: "",
            profilePhotoUrl = profilePhotoUrl.value ?: "",
            favouriteGenres = chosenGenres.value ?: emptyList(),
            favouriteAuthors = chosenFavouriteAuthors.getFavoriteAuthors(),
            favouriteBooks = chosenFavouriteBooks.value ?: emptyList(),
            wantToReadBooks = chosenWantToReadBooks.value ?: emptyList(),
            quote = quote.value ?: GoQuote(),
        )

        creatingProfileJob?.cancel()
        creatingProfileJob = viewModelScope.launch {
            _creatingProfileStatus.postValue(Status.LOADING)
            interactor.uploadNewProfile(profile)
        }
    }

    override fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _creatingProfileStatus.postValue(Status.ERROR)
        super.handleErrorResult(error)
    }

    private fun addFavouriteBook(book: GoogleBook) {
        val books = _chosenFavouriteBooks.value?.toMutableList() ?: mutableListOf()
        books.add(0, book)
        _chosenFavouriteBooks.postValue(books)
    }

    private fun removeFavouriteBook(book: GoogleBook) {
        val books = _chosenFavouriteBooks.value?.toMutableList() ?: mutableListOf()
        books.remove(book)
        _chosenFavouriteBooks.postValue(books)
    }

    private fun addWantToReadBook(book: GoogleBook) {
        val books = _chosenWantToReadBooks.value?.toMutableList() ?: mutableListOf()
        books.add(0, book)
        _chosenWantToReadBooks.postValue(books)
    }

    private fun removeWantToReadBook(book: GoogleBook) {
        val books = _chosenWantToReadBooks.value?.toMutableList() ?: mutableListOf()
        books.remove(book)
        _chosenWantToReadBooks.postValue(books)
    }

    private fun handleCreatingProfileResult(result: RetrofitResult<UserResponseModel>) {
        when (result) {
            is RetrofitResult.Failure<*> -> handleErrorResult(result)
            is RetrofitResult.Success -> handleSuccessResult(result)
        }
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<UserResponseModel>) {
        _creatingProfileStatus.postValue(Status.DONE)
    }

    private fun LiveData<List<Pair<Author, CreatingProfileAuthorChooser.AuthorPosition>>>.getFavoriteAuthors(): List<Author> {
        val value = value ?: return emptyList()
        val result = mutableListOf<Author>()
        value.forEach { pair ->
            result.add(pair.first)
        }
        return result
    }
}