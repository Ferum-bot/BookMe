package com.levit.book_me.ui.fragments.creating_profile.search_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core_base.di.SearchBooksScope
import com.levit.book_me.interactors.interfaces.SearchBooksInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@SearchBooksScope
class SearchBooksViewModel @Inject constructor(
    private val interactor: SearchBooksInteractor
): BaseViewModel() {

    enum class Statuses {
        NOT_SEARCHING, SEARCHING, FOUND, ERROR, NOTHING_FOUND
    }

    private val _currentStatus: MutableLiveData<Statuses> = MutableLiveData(Statuses.NOT_SEARCHING)
    val currentStatus: LiveData<Statuses> = _currentStatus

    private val _searchResult: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val searchResult: LiveData<List<GoogleBook>> = _searchResult

    private val _someBooksIsChosen: MutableLiveData<Boolean> = MutableLiveData(false)
    val someBooksIsChosen: LiveData<Boolean> = _someBooksIsChosen

    private val chosenBooks: MutableList<GoogleBook> = mutableListOf()

    private var searchJob: Job? = null

    init {
        viewModelScope.launch {
            interactor.books.collect { result ->
                handleResult(result)
            }
        }
    }

    fun searchBooks(query: String) {
        searchJob?.cancel()
        chosenBooks.clear()

        if(query.isBlank()) {
            _currentStatus.postValue(Statuses.NOT_SEARCHING)
            return
        }

        searchJob = viewModelScope.launch {
            _currentStatus.postValue(Statuses.SEARCHING)
            interactor.searchBooks(query)
        }
    }

    fun addChosenBook(book: GoogleBook) {
        chosenBooks.add(book)
        _someBooksIsChosen.postValue(true)
    }

    fun removeChosenBook(book: GoogleBook) {
        chosenBooks.remove(book)
        if (chosenBooks.isEmpty()) {
            _someBooksIsChosen.postValue(false)
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
        val books = result.data
        if (books.isEmpty()) {
            _currentStatus.postValue(Statuses.NOTHING_FOUND)
        } else {
            _currentStatus.postValue(Statuses.FOUND)
        }
        _searchResult.postValue(books)
    }
}