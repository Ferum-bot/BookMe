package com.levit.book_me.ui.fragments.creating_profile.search_favourite_authors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.Auth
import com.levit.book_me.R
import com.levit.book_me.core.models.Author
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.interfaces.SearchFavouriteAuthorsInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class SearchFavouriteAuthorsViewModel @Inject constructor(
    private val interactor: SearchFavouriteAuthorsInteractor
): ViewModel() {

    enum class SearchStatus{
        SEARCHING, NOT_SEARCHING, NOTHING_FOUND, FOUND
    }

    private val _searchResult: MutableLiveData<List<Author>> = MutableLiveData(emptyList())
    val searchResult: LiveData<List<Author>> = _searchResult

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _errorMessageId: MutableLiveData<Int?> = MutableLiveData(null)
    val errorMessageId: LiveData<Int?> = _errorMessageId

    private val _currentStatus: MutableLiveData<SearchStatus> = MutableLiveData(SearchStatus.NOT_SEARCHING)
    val currentStatus: LiveData<SearchStatus> = _currentStatus

    private var currentSearchJob: Job? = null

    init {
        viewModelScope.launch {
            interactor.searchAuthorsResult.collectLatest {
                handleSearchResult(it)
            }
        }
    }

    fun onSearchTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        currentSearchJob?.cancel()

        if (text.isNotBlank()) {
            searchAuthors(text.toString())
        }
        else {
            _currentStatus.postValue(SearchStatus.NOT_SEARCHING)
        }
    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
        _errorMessageId.value = null
    }

    private fun searchAuthors(text: String) {
        _currentStatus.postValue(SearchStatus.SEARCHING)
        currentSearchJob = viewModelScope.launch {
            interactor.searchAuthors(text)
        }
    }

    private fun handleSearchResult(result: RetrofitResult<List<Author>>) {
        when (result) {
            is RetrofitResult.Success -> handleSuccessResult(result)
            is RetrofitResult.Failure<*> -> handleFailureResult(result)
        }
    }

    private fun handleFailureResult(result: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(SearchStatus.NOT_SEARCHING)
        _searchResult.postValue(emptyList())

        when (result) {

            is RetrofitResult.Failure.HttpError -> {
                if (result.statusMessage.isNullOrBlank()) {
                    _errorMessageId.postValue(R.string.something_went_wrong)
                }
                else {
                    _errorMessage.postValue(result.statusMessage)
                }
            }

            is RetrofitResult.Failure.Error -> {
                if (result.messageId != null) {
                    _errorMessageId.postValue(result.messageId)
                    return
                }
                if (result.message != null) {
                    _errorMessage.postValue(result.message)
                }
                else {
                    _errorMessageId.postValue(R.string.something_went_wrong)
                }
            }
        }
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<Author>>) {
        val authors = result.data
        if (authors.isEmpty()) {
            _currentStatus.postValue(SearchStatus.NOTHING_FOUND)
            _searchResult.postValue(emptyList())
        }
        else {
            _currentStatus.postValue(SearchStatus.FOUND)
            _searchResult.postValue(authors)
        }
    }
}