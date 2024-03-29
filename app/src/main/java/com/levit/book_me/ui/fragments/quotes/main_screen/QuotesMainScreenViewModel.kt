package com.levit.book_me.ui.fragments.quotes.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.interactors.quotes.QuotesMainScreenInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesMainScreenViewModel @Inject constructor(
    private val interactor: QuotesMainScreenInteractor
): BaseViewModel() {

    enum class QuotesMainScreenStatuses {
        LOADING, ERROR, LOADED
    }

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

    fun launchGettingScreenModel() {
        _currentStatus.postValue(QuotesMainScreenStatuses.LOADING)

        viewModelScope.launch {
            interactor.getMainScreenModel()
        }
    }

    private fun handleModelResult(result: RetrofitResult<QuotesMainScreenModel>) = when(result) {
        is RetrofitResult.Success -> {
            handleSuccessResult(result)
        }
        is RetrofitResult.Failure<*> -> {
            handleErrorResult(result)
        }
    }

    override fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(QuotesMainScreenStatuses.ERROR)
        super.handleErrorResult(error)
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<QuotesMainScreenModel>) {
        _currentStatus.postValue(QuotesMainScreenStatuses.LOADED)
        val model = result.data
        _screenModel.postValue(model)
    }
}