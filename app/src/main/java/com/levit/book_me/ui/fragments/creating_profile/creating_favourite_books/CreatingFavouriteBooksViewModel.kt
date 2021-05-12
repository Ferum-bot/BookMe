package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.interfaces.CreatingBooksInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class CreatingFavouriteBooksViewModel @Inject constructor(
    private val interator: CreatingBooksInteractor
): BaseViewModel() {

    private val _popularBooks: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val popularBooks: LiveData<List<GoogleBook>> = _popularBooks

    private val chosenBooks: MutableList<GoogleBook> = mutableListOf()

    init {
        viewModelScope.launch {
            interator.popularBooks.collect{
                handleResult(it)
            }
        }

        viewModelScope.launch {
            interator.getPopularBooks()
        }
    }

    fun addChosenBook(book: GoogleBook) {
        val books = _popularBooks.value?.toMutableList() ?: mutableListOf()
        books.add(0, book)
        chosenBooks.add(book)
        _popularBooks.postValue(books)
    }

    fun removeChosenBook(book: GoogleBook) {
        chosenBooks.remove(book)
    }

    private fun handleResult(result: RetrofitResult<List<GoogleBook>>) {
        when(result) {
            is RetrofitResult.Failure<*> -> handleErrorResult(result)
            is RetrofitResult.Success -> handleSuccessResult(result)
        }
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<GoogleBook>>) {
        val books = result.data
        _popularBooks.postValue(books)
    }
}