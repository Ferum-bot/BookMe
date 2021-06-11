package com.levit.book_me.ui.fragments.creating_profile.creating_books_want_to_read

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.creating_profile.SearchBooksInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class CreatingBooksWantToReadViewModel @Inject constructor(
    private val interator: SearchBooksInteractor
): BaseViewModel() {

    enum class Statuses {
        LOADING, LOADED, ERROR
    }

    private val _mostChosenBooks: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val mostChosenBooks: LiveData<List<GoogleBook>> = _mostChosenBooks

    private val _currentStatus: MutableLiveData<Statuses> = MutableLiveData()
    val currentStatus: LiveData<Statuses> = _currentStatus

    private val _isChosenEnoughBooks: MutableLiveData<Boolean> = MutableLiveData(true)
    val isChosenEnoughBooks: LiveData<Boolean> = _isChosenEnoughBooks

    private val chosenBooks = mutableListOf<GoogleBook>()

    private var searchMostChosenBooksJob: Job? = null

    init {
        viewModelScope.launch {
            interator.books.collect { result ->
                handleResult(result)
            }
        }

        getMostChosenBooks()
    }

    fun getMostChosenBooks() {
        searchMostChosenBooksJob?.cancel()
        searchMostChosenBooksJob = viewModelScope.launch {
            _currentStatus.postValue(Statuses.LOADING)
            interator.getMostChosenBooks()
        }
    }

    fun addChosenBook(book: GoogleBook) {
        val books = _mostChosenBooks.value?.toMutableList() ?: mutableListOf()
        if (!books.contains(book)) {
            books.add(0, book)
            _mostChosenBooks.postValue(books)
        }
        chosenBooks.add(book)
        checkBooksCount()
    }

    fun addChosenBooks(books: List<GoogleBook>) {
        val currentBooks = _mostChosenBooks.value?.toMutableList() ?: mutableListOf()
        books.forEach { book ->
            currentBooks.remove(book)
            currentBooks.add(0, book)

            chosenBooks.remove(book)
            chosenBooks.add(book)
        }
        _mostChosenBooks.postValue(currentBooks)
        checkBooksCount()
    }

    fun removeChosenBook(book: GoogleBook) {
        chosenBooks.remove(book)
    }

    fun everythingIsValid(): Boolean {
        if (chosenBooks.size < CreatingProfileConstants.MIN_COUNT_OF_WANT_TO_READ_BOOKS) {
            _isChosenEnoughBooks.postValue(false)
            return false
        }
        if(chosenBooks.size > CreatingProfileConstants.MAX_COUNT_OF_WANT_TO_READ_BOOKS) {
            _errorMessageId.postValue(R.string.you_can_choose_not_more_books)
            return false
        }
        return true
    }

    private fun checkBooksCount() {
        if(chosenBooks.size >= CreatingProfileConstants.MIN_COUNT_OF_WANT_TO_READ_BOOKS) {
            _isChosenEnoughBooks.postValue(true)
        }
    }

    private fun handleResult(result: RetrofitResult<List<GoogleBook>>) =
    when(result) {
        is RetrofitResult.Failure<*> -> handleErrorResult(result)
        is RetrofitResult.Success -> handleSuccessResult(result)
    }

    override fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(Statuses.ERROR)
        super.handleErrorResult(error)
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<GoogleBook>>) {
        _currentStatus.postValue(Statuses.LOADED)
        val books = result.data
        _mostChosenBooks.postValue(books)
    }
}