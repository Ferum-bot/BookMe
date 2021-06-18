package com.levit.book_me.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.R
import com.levit.book_me.network.network_result_data.RetrofitResult

abstract class BaseViewModel: ViewModel() {

    protected val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?> = _errorMessage

    protected val _errorMessageId: MutableLiveData<Int?> = MutableLiveData(null)
    val errorMessageId: LiveData<Int?> = _errorMessageId

    protected val _successMessage: MutableLiveData<String?> = MutableLiveData(null)
    val successMessage: LiveData<String?> = _successMessage

    protected val _successMessageId: MutableLiveData<Int?> = MutableLiveData(null)
    val successMessageId: LiveData<Int?> = _successMessageId

    open fun errorMessageHasShown() {
        _errorMessageId.postValue(null)
        _errorMessage.postValue(null)
    }

    open fun successMessageHasShown() {
        _successMessage.postValue(null)
        _successMessageId.postValue(null)
    }

    protected open fun handleErrorResult(error: RetrofitResult.Failure<*>) {
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