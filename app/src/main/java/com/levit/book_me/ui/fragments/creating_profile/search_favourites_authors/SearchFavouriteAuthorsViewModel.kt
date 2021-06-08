package com.levit.book_me.ui.fragments.creating_profile.search_favourites_authors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core.models.Author
import com.levit.book_me.core_base.di.SearchFavouriteAuthorsScope
import com.levit.book_me.interactors.interfaces.SearchFavouriteAuthorsInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@SearchFavouriteAuthorsScope
class SearchFavouriteAuthorsViewModel @Inject constructor(
    private val interactor: SearchFavouriteAuthorsInteractor
): BaseViewModel() {

    enum class SearchStatus{
        SEARCHING, NOT_SEARCHING, NOTHING_FOUND, FOUND
    }

    private val _searchResult: MutableLiveData<List<Author>> = MutableLiveData(emptyList())
    val searchResult: LiveData<List<Author>> = _searchResult

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

    private fun searchAuthors(text: String) {
        _currentStatus.postValue(SearchStatus.SEARCHING)
        _searchResult.postValue(emptyList())
        currentSearchJob = viewModelScope.launch {
            interactor.searchAuthors(text)
        }
    }

    private fun handleSearchResult(result: RetrofitResult<List<Author>>) {
        when (result) {
            is RetrofitResult.Success -> handleSuccessResult(result)
            is RetrofitResult.Failure<*> -> handleErrorResult(result)
        }
    }

    override fun handleErrorResult(error: RetrofitResult.Failure<*>) {
        _currentStatus.postValue(SearchStatus.NOT_SEARCHING)
        _searchResult.postValue(emptyList())
        super.handleErrorResult(error)
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<Author>>) {
        val authors = result.data
        if (authors.isEmpty()) {
            _searchResult.postValue(emptyList())
            _currentStatus.postValue(SearchStatus.NOTHING_FOUND)
        }
        else {
            _searchResult.postValue(authors)
            _currentStatus.postValue(SearchStatus.FOUND)
        }
    }
}