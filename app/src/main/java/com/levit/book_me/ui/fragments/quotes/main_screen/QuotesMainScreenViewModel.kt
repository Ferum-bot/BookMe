package com.levit.book_me.ui.fragments.quotes.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.core_base.di.QuotesScreenScope
import com.levit.book_me.interactors.interfaces.QuotesMainScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@QuotesScreenScope
class QuotesMainScreenViewModel @Inject constructor(
    private val interactor: QuotesMainScreenInteractor
): ViewModel() {

    companion object {
        private const val DELAY_TIME = 500L
    }

    enum class QuotesMainScreenStatuses {
        LOADING, ERROR, LOADED
    }

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _errorMessageId: MutableLiveData<Int?> = MutableLiveData(null)
    val errorMessageId: LiveData<Int?> = _errorMessageId

    private val _numberOfTags: MutableLiveData<Int?> = MutableLiveData(null)
    val numberOfTags: LiveData<Int?> = _numberOfTags

    private val _numberOfAuthors: MutableLiveData<Int?> = MutableLiveData(null)
    val numberOfAuthors: LiveData<Int?> = _numberOfAuthors

    private val _randomQuotes: MutableLiveData<List<GoQuote>?> = MutableLiveData(null)
    val randomQuotes: LiveData<List<GoQuote>?> = _randomQuotes

    private val _allDataIsAvailable: MutableLiveData<Boolean> = MutableLiveData(false)
    val allDataIsAvailable: LiveData<Boolean> = _allDataIsAvailable

    private val _currentStatus: MutableLiveData<QuotesMainScreenStatuses> = MutableLiveData(QuotesMainScreenStatuses.LOADING)
    val currentStatus: LiveData<QuotesMainScreenStatuses> = _currentStatus

    private val allDataIsReady: Boolean
    get() = _numberOfTags.value != null && _numberOfAuthors.value != null  && _randomQuotes.value != null

    init {
        launchCollecting()
        launchRemoteQuery()
    }

    fun errorMessageHasShown() {
        _errorMessage.postValue(null)
        _errorMessageId.postValue(null)
    }

    private fun launchCollecting() {
        viewModelScope.launch {
            interactor.numberOfTags.collect { result ->
                handleNumberOfTagsResult(result)
            }
        }

        viewModelScope.launch {
            interactor.numberOfAuthors.collect { result ->
                handleNumberOfAuthorsResult(result)
            }
        }

        viewModelScope.launch {
            interactor.randomQuotes.collect { result ->
                handleRandomQuotesResult(result)
            }
        }
    }

    private fun launchRemoteQuery() {
        _currentStatus.postValue(QuotesMainScreenStatuses.LOADING)

        viewModelScope.launch {
            interactor.getNumberOfTags()
        }

        viewModelScope.launch {
            interactor.getNumberOfAuthors()
        }

        viewModelScope.launch {
            interactor.getRandomQuotes()
        }
    }

    private suspend fun handleNumberOfTagsResult(result: RetrofitResult<Int>) = when(result) {
        is RetrofitResult.Success -> {
            _numberOfTags.postValue(result.data)
            checkDataAvailability()
        }
        is RetrofitResult.Failure<*> -> {
            handleErrorResult(result)
        }
    }

    private suspend fun handleNumberOfAuthorsResult(result: RetrofitResult<Int>) = when(result) {
        is RetrofitResult.Success -> {
            _numberOfAuthors.postValue(result.data)
            checkDataAvailability()
        }
        is RetrofitResult.Failure<*> -> {
            handleErrorResult(result)
        }
    }

    private suspend fun handleRandomQuotesResult(result: RetrofitResult<List<GoQuote>>) = when(result) {
        is RetrofitResult.Success -> {
            _randomQuotes.postValue(result.data)
            checkDataAvailability()
        }
        is RetrofitResult.Failure<*> -> {
            handleErrorResult(result)
        }
    }

    private fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(QuotesMainScreenStatuses.ERROR)
        when(error) {
            is RetrofitResult.Failure.HttpError -> {
                if (error.statusMessage == null) {
                    _errorMessageId.postValue(R.string.something_went_wrong)
                } else {
                    _errorMessage.postValue(error.statusMessage)
                }
            }

            is RetrofitResult.Failure.Error -> {
                if (error.messageId != null) {
                    _errorMessageId.postValue(error.messageId)
                    return
                }
                if (error.message != null) {
                    _errorMessage.postValue(error.message)
                } else {
                    _errorMessageId.postValue(R.string.something_went_wrong)
                }
            }
        }
    }

    private suspend fun checkDataAvailability() {
        delay(DELAY_TIME)
        if (allDataIsReady) {
            _allDataIsAvailable.postValue(true)
            _currentStatus.postValue(QuotesMainScreenStatuses.LOADED)
        }
    }
}