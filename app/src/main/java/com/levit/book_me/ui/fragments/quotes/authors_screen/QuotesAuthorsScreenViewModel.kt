package com.levit.book_me.ui.fragments.quotes.authors_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.interactors.quotes.QuotesAuthorsScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesAuthorsScreenViewModel @Inject constructor(
    private val interator: QuotesAuthorsScreenInteractor
): BaseViewModel() {

    enum class Statuses {
        LOADING, LOADED, ERROR
    }

    private val _currentStatus: MutableLiveData<Statuses> = MutableLiveData()
    val currentStatus: LiveData<Statuses> = _currentStatus

    private val _authors: MutableLiveData<List<QuoteAuthorModel>> = MutableLiveData()
    val authors: LiveData<List<QuoteAuthorModel>> = _authors

    private val allAuthors: MutableList<QuoteAuthorModel> = mutableListOf()

    init {
        viewModelScope.launch {
            interator.authors.collect { result ->
                handleAuthorsResult(result)
            }
        }

        getAllAuthors()
    }

    fun getAllAuthors() {
        viewModelScope.launch {
            _currentStatus.postValue(Statuses.LOADING)
            interator.getAllAuthors()
        }
    }

    fun getAuthorsByQuery(query: String?) {
        if (query.isNullOrBlank()) {
            _authors.postValue(allAuthors)
            return
        }
        val resultAuthors = allAuthors.filter { author ->
            author.fullName.contains(query, true)
        }
        _authors.postValue(resultAuthors)
    }

    private fun handleAuthorsResult(result: RetrofitResult<List<QuoteAuthorModel>>) = when(result) {
        is RetrofitResult.Success -> {
            handleSuccessResult(result)
        }
        is RetrofitResult.Failure<*> -> {
            handleErrorResult(result)
        }
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<QuoteAuthorModel>>) {
        _currentStatus.postValue(Statuses.LOADED)
        val authorsList = result.data
        _authors.postValue(authorsList)
        allAuthors.clear()
        allAuthors.addAll(authorsList)
    }

    override fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(Statuses.ERROR)
        super.handleErrorResult(error)
    }
}