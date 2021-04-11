package com.levit.book_me.ui.fragments.creating_profile.search_favourite_authors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.Author
import com.levit.book_me.core_base.di.CreatingProfileScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class SearchFavouriteAuthorsViewModel @Inject constructor(): ViewModel() {

    private val _searchResult: MutableLiveData<List<Author>?> = MutableLiveData(null)
    val searchResult: LiveData<List<Author>?> = _searchResult

    private var currentSearchJob: Job? = null

    fun onSearchTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        currentSearchJob?.cancel()

        if (text.isNotBlank()) {
            // TODO
        }
        else {
            _searchResult.postValue(null)
        }
    }
}