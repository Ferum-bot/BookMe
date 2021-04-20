package com.levit.book_me.ui.fragments.creating_profile.utills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.ui.custom_view.CreatingProfileAuthorChooser

/**
 * Temporary solution to pass chosen favourite authors
 * between fragments.
 * Remove this lately. Rewrite to shared ViewModel.
 */
object FavouriteAuthorsStorage {

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

    fun setAuthorTo(author: Author, position: CreatingProfileAuthorChooser.AuthorPosition) {
        when (position) {
            CreatingProfileAuthorChooser.AuthorPosition.FIRST_POSITION -> _firstAuthor.value = author
            CreatingProfileAuthorChooser.AuthorPosition.SECOND_POSITION -> _secondAuthor.value = author
            CreatingProfileAuthorChooser.AuthorPosition.THIRD_POSITION -> _thirdAuthor.value = author
            CreatingProfileAuthorChooser.AuthorPosition.FOURS_POSITION -> _foursAuthor.value = author
            CreatingProfileAuthorChooser.AuthorPosition.FIVES_POSITION -> _fivesAuthor.value = author
        }
    }

    fun removeAuthorFrom(position: CreatingProfileAuthorChooser.AuthorPosition) {
        when(position) {
            CreatingProfileAuthorChooser.AuthorPosition.FIRST_POSITION -> _firstAuthor.value = null
            CreatingProfileAuthorChooser.AuthorPosition.SECOND_POSITION -> _secondAuthor.value = null
            CreatingProfileAuthorChooser.AuthorPosition.THIRD_POSITION -> _thirdAuthor.value = null
            CreatingProfileAuthorChooser.AuthorPosition.FOURS_POSITION -> _foursAuthor.value = null
            CreatingProfileAuthorChooser.AuthorPosition.FIVES_POSITION -> _fivesAuthor.value = null
        }
    }
}