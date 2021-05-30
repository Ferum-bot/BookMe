package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.interfaces.CreatingFavouriteGenresInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class CreatingFavouriteGenresViewModel @Inject constructor(
    private val interactor: CreatingFavouriteGenresInteractor
): BaseViewModel() {

    private val _genres: MutableLiveData<List<Genre>> = MutableLiveData()
    val genres: LiveData<List<Genre>> = _genres

    init {

        viewModelScope.launch {
            interactor.genres.collect { result ->
                handleGenresResult(result)
            }
        }

        getGenres()
    }

    fun getGenres() {
        viewModelScope.launch {
            interactor.getGenres()
        }
    }

    private fun handleGenresResult(result: RetrofitResult<List<Genre>>) {
        when(result) {
            is RetrofitResult.Failure<*> -> handleErrorResult(result)
            is RetrofitResult.Success -> handleSuccessResult(result)
        }
    }

    private fun handleSuccessResult(result: RetrofitResult.Success<List<Genre>>) {
        val genres = result.data
        _genres.postValue(genres)
    }
}