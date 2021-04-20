package com.levit.book_me.ui.fragments.creating_profile.utills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.core.models.Author

/**
 * Temporary solution to pass chosen favourite authors
 * between fragments.
 * Remove this lately. Rewrite to shared ViewModel.
 */
object FavouriteAuthorsStorage {

    private const val FIRST_POSITION = 0
    private const val SECOND_POSITION = 1
    private const val THIRD_POSITION = 2
    private const val FOURS_POSITION = 3
    private const val FIVES_POSITION = 4

    private val _firstAuthor: MutableLiveData<Author?> = MutableLiveData(null)
    val firstAuthor: LiveData<Author?> = _firstAuthor

    private val _secondAuthor: MutableLiveData<Author?> = MutableLiveData(null)
    val secondAuthor: LiveData<Author?> = _secondAuthor

    private val _thirdAuthor: MutableLiveData<Author?> = MutableLiveData(null)
    val thirdAuthor: LiveData<Author?> = _thirdAuthor

    private val _foursAuthor: MutableLiveData<Author?> = MutableLiveData(null)
    val foursAuthor: LiveData<Author?> = _foursAuthor

    private val _fivesAuthor: MutableLiveData<Author?> = MutableLiveData(null)
    val fivesAuthor: LiveData<Author?> = _fivesAuthor

    fun setAuthorTo(author: Author, position: Int) {
        when (position) {
            FIRST_POSITION -> _firstAuthor.value = author
            SECOND_POSITION -> _secondAuthor.value = author
            THIRD_POSITION -> _thirdAuthor.value = author
            FOURS_POSITION -> _foursAuthor.value = author
            FIVES_POSITION -> _fivesAuthor.value = author
        }
    }

    fun removeAuthorFrom(position: Int) {
        when(position) {
            FIRST_POSITION -> _firstAuthor.value = null
            SECOND_POSITION -> _secondAuthor.value = null
            THIRD_POSITION -> _thirdAuthor.value = null
            FOURS_POSITION -> _foursAuthor.value = null
            FIVES_POSITION -> _fivesAuthor.value = null
        }
    }
}