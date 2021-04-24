package com.levit.book_me.ui.fragments.quotes.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core.models.GoQuote
import com.levit.book_me.core.models.QuotesMainScreenModel
import com.levit.book_me.core_base.di.QuotesScreenScope
import com.levit.book_me.interactors.interfaces.QuotesMainScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesMainScreenViewModel @Inject constructor(
    private val interactor: QuotesMainScreenInteractor
): ViewModel() {

    enum class QuotesMainScreenStatuses {
        LOADING, ERROR, LOADED
    }

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _errorMessageId: MutableLiveData<Int?> = MutableLiveData(null)
    val errorMessageId: LiveData<Int?> = _errorMessageId

    private val _screenModel: MutableLiveData<QuotesMainScreenModel?> = MutableLiveData(null)
    val screenModel: LiveData<QuotesMainScreenModel?> = _screenModel

    private val _currentStatus: MutableLiveData<QuotesMainScreenStatuses> = MutableLiveData(QuotesMainScreenStatuses.LOADING)
    val currentStatus: LiveData<QuotesMainScreenStatuses> = _currentStatus

    init {
        viewModelScope.launch {
            interactor.screenModel.collect { result ->
                handleModelResult(result)
            }
        }
        
        launchGettingScreenModel()
    }

    fun errorMessageHasShown() {
        _errorMessage.postValue(null)
        _errorMessageId.postValue(null)
    }

    private fun launchGettingScreenModel() {
        _currentStatus.postValue(QuotesMainScreenStatuses.LOADING)

        viewModelScope.launch {
            interactor.getRandomQuotes()
        }

        viewModelScope.launch {
            interactor.getNumberOfAuthors()
        }

        viewModelScope.launch {
            interactor.getNumberOfTags()
        }
    }

    private fun handleModelResult(result: RetrofitResult<QuotesMainScreenModel>) = when(result) {
        is RetrofitResult.Success -> {
            _currentStatus.postValue(QuotesMainScreenStatuses.LOADED)
            val model = result.data
            _screenModel.postValue(model)
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

}