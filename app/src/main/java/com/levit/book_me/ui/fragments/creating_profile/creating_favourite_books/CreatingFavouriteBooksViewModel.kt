package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.interfaces.SearchBooksInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class CreatingFavouriteBooksViewModel @Inject constructor(
    private val interator: SearchBooksInteractor
): BaseViewModel() {

    enum class Statuses {
        LOADING, LOADED, ERROR
    }

    private val _popularBooks: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val popularBooks: LiveData<List<GoogleBook>> = _popularBooks

    private val _currentStatus: MutableLiveData<Statuses> = MutableLiveData()
    val currentStatus: LiveData<Statuses> = _currentStatus

    private val _isChosenEnoughBooks: MutableLiveData<Boolean> = MutableLiveData(true)
    val isChosenEnoughBooks: LiveData<Boolean> = _isChosenEnoughBooks

    private val chosenBooks: MutableList<GoogleBook> = mutableListOf()

    private var searchPopularBooksJob: Job? = null

    init {
        viewModelScope.launch {
            interator.books.collect{ result ->
                handleResult(result)
            }
        }

        getPopularBooks()
    }

    fun getPopularBooks() {
        searchPopularBooksJob?.cancel()
        searchPopularBooksJob = viewModelScope.launch {
            _currentStatus.postValue(Statuses.LOADING)
            interator.getPopularBooks()
        }
    }

    fun addChosenBook(book: GoogleBook) {
        val books = _popularBooks.value?.toMutableList() ?: mutableListOf()
        if (!books.contains(book)) {
            books.add(0, book)
            _popularBooks.postValue(books)
        }
        chosenBooks.add(book)
        checkBooksCount()
    }

    fun addChosenBooks(books: List<GoogleBook>) {
        val currentBooks = _popularBooks.value?.toMutableList() ?: mutableListOf()
        books.forEach { book ->
            currentBooks.remove(book)
            currentBooks.add(0, book)

            chosenBooks.remove(book)
            chosenBooks.add(book)
        }
        _popularBooks.postValue(currentBooks)
        checkBooksCount()
    }

    fun removeChosenBook(book: GoogleBook) {
        chosenBooks.remove(book)
    }

    fun everythingIsValid(): Boolean {
        if (chosenBooks.size < CreatingProfileConstants.MIN_COUNT_OF_FAVOURITE_BOOKS) {
            _isChosenEnoughBooks.postValue(false)
            return false
        }
        if (chosenBooks.size > CreatingProfileConstants.MAX_COUNT_OF_FAVOURITE_BOOKS) {
            _errorMessageId.postValue(R.string.you_can_choose_not_more_books)
            return false
        }
        return true
    }

    private fun checkBooksCount() {
        if (chosenBooks.size >= CreatingProfileConstants.MIN_COUNT_OF_FAVOURITE_BOOKS) {
            _isChosenEnoughBooks.postValue(true)
        }
    }

    private fun handleResult(result: RetrofitResult<List<GoogleBook>>) {
        when(result) {
            is RetrofitResult.Failure<*> -> handleErrorResult(result)
            is RetrofitResult.Success -> handleSuccessResult(result)
        }
    }

    override fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(Statuses.ERROR)
        super.handleErrorResult(error)
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<GoogleBook>>) {
        _currentStatus.postValue(Statuses.LOADED)
        val books = result.data
        _popularBooks.postValue(books)
    }
}