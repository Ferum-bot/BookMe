package com.levit.book_me.ui.fragments.quotes.tags_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.quote.GoQuotesTag
import com.levit.book_me.interactors.quotes.QuotesTagsScreenInterator
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesTagsScreenViewModel @Inject constructor(
    private val interactor: QuotesTagsScreenInterator
): BaseViewModel() {

    enum class Statuses {
        LOADING, LOADED, ERROR
    }

    private val _currentStatus: MutableLiveData<Statuses> = MutableLiveData()
    val currentStatus: LiveData<Statuses> = _currentStatus

    private val _tags: MutableLiveData<List<GoQuotesTag>> = MutableLiveData()
    val tags: LiveData<List<GoQuotesTag>> = _tags

    init {
        _currentStatus.postValue(Statuses.LOADING)

        viewModelScope.launch {
            interactor.tags.collect { result ->
                handleTagsResult(result)
            }
        }

        getAllTags()
    }

    fun getAllTags() {
        viewModelScope.launch {
            interactor.getAllTags()
        }
    }

    private fun handleTagsResult(result: RetrofitResult<List<GoQuotesTag>>) = when (result) {
        is RetrofitResult.Success -> {
            handleSuccessResult(result)
        }
        is RetrofitResult.Failure<*> -> {
            handleErrorResult(result)
        }
    }

    override fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(Statuses.ERROR)
        super.handleErrorResult(error)
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<GoQuotesTag>>) {
        _currentStatus.postValue(Statuses.LOADED)
        val tagsList = result.data
        _tags.postValue(tagsList)
    }
}