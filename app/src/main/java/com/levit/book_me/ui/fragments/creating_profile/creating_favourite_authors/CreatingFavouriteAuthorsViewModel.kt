package com.levit.book_me.ui.fragments.creating_profile.creating_favourite_authors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.core.models.Author
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.ui.base.BaseViewModel
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants
import javax.inject.Inject

@CreatingProfileScope
class CreatingFavouriteAuthorsViewModel @Inject constructor(): BaseViewModel() {

    private val _isChosenEnoughAuthors: MutableLiveData<Boolean> = MutableLiveData(true)
    val isChosenEnoughAuthors: LiveData<Boolean> = _isChosenEnoughAuthors

    private val chosenAuthors: MutableList<Author> = mutableListOf()

    fun setChosenAuthors(authors: List<Author>) {
        chosenAuthors.clear()
        chosenAuthors.addAll(authors)

        if (authors.size >= CreatingProfileConstants.MIN_COUNT_OF_FAVOURITE_AUTHORS) {
            _isChosenEnoughAuthors.postValue(true)
        }
    }

    fun everythingIsValid(): Boolean {
        if (chosenAuthors.size >= CreatingProfileConstants.MIN_COUNT_OF_FAVOURITE_AUTHORS) {
            _isChosenEnoughAuthors.postValue(true)
            return true
        }
        _isChosenEnoughAuthors.postValue(false)
        return false
    }
}