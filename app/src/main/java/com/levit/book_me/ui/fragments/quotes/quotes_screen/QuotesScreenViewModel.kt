package com.levit.book_me.ui.fragments.quotes.quotes_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuotesTypes
import com.levit.book_me.interactors.quotes.QuotesScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesScreenViewModel @Inject constructor(
    private val interactor: QuotesScreenInteractor
): BaseViewModel() {

    enum class Statuses {
        LOADING, LOADED, ERROR
    }

    private val _currentStatus: MutableLiveData<Statuses> = MutableLiveData()
    val currentStatus: LiveData<Statuses> = _currentStatus

    private val _quotes: MutableLiveData<List<QuoteModel>> = MutableLiveData()
    val quotes: LiveData<List<QuoteModel>> = _quotes

    init {
        viewModelScope.launch {
            interactor.quotes.collect { result ->
                handleResult(result)
            }
        }
    }

    fun searchQuotes(type: QuotesTypes, query: String) {
        _currentStatus.postValue(Statuses.LOADING)
        viewModelScope.launch {
            interactor.searchQuotes(type, query)
        }
    }

    private fun handleResult(result: RetrofitResult<List<QuoteModel>>) = when(result) {
        is RetrofitResult.Success -> {
            handleSuccessResult(result)
        }
        is RetrofitResult.Failure<*> -> {
            handleErrorResult(result)
        }
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<QuoteModel>>) {
        _currentStatus.postValue(Statuses.LOADED)
        val quotes = result.data
        _quotes.postValue(quotes)
    }

    override fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(Statuses.ERROR)
        super.handleErrorResult(error)
    }
}