package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.creating_profile.CreatingFavouriteGenresInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.ui.base.BaseViewModel
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class CreatingFavouriteGenresViewModel @Inject constructor(
    private val interactor: CreatingFavouriteGenresInteractor
): BaseViewModel() {

    private val _genres: MutableLiveData<List<Genre>> = MutableLiveData()
    val genres: LiveData<List<Genre>> = _genres

    private val _genresAreChosen: MutableLiveData<Boolean> = MutableLiveData()
    val genresAreChosen: LiveData<Boolean> = _genresAreChosen

    private val _chosenGenres: MutableList<Genre> = mutableListOf()
    val chosenGenres: List<Genre> = _chosenGenres

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

    fun addGenre(genre: Genre) {
        _chosenGenres.add(genre)
        if (chosenGenres.size >= CreatingProfileConstants.MIN_COUNT_CHOSEN_GENRES) {
            _genresAreChosen.postValue(true)
        }
    }

    fun removeGenre(genre: Genre) {
        _chosenGenres.remove(genre)
    }

    fun allGenresAreChosen(): Boolean {
        if (chosenGenres.size < CreatingProfileConstants.MIN_COUNT_CHOSEN_GENRES) {
            _genresAreChosen.postValue(false)
            return false
        }
        if (chosenGenres.size > CreatingProfileConstants.MAX_COUNT_CHOSEN_GENRES) {
            _errorMessageId.postValue(R.string.choose_from_to_genres)
            return false
        }
        return true
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