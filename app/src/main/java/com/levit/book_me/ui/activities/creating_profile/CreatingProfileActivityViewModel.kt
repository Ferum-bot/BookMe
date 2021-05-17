package com.levit.book_me.ui.activities.creating_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.ui.custom_view.CreatingProfileAuthorChooser
import com.levit.book_me.core_base.di.CreatingProfileScope
import javax.inject.Inject

@CreatingProfileScope
class CreatingProfileActivityViewModel @Inject constructor(

): ViewModel() {

    private val _chosenFavouriteAuthors:
        MutableLiveData<List<Pair<Author, CreatingProfileAuthorChooser.AuthorPosition>>> =
        MutableLiveData()
    val chosenFavouriteAuthors:
        LiveData<List<Pair<Author, CreatingProfileAuthorChooser.AuthorPosition>>> =
        _chosenFavouriteAuthors

    fun safeFavouriteAuthor(author: Author, position: CreatingProfileAuthorChooser.AuthorPosition) {
        val authors = _chosenFavouriteAuthors.value?.toMutableList()
            ?: mutableListOf()
        val authorPair = author to position
        authors.add(authorPair)
        _chosenFavouriteAuthors.postValue(authors)
    }

    fun removeFavouriteAuthor(position: CreatingProfileAuthorChooser.AuthorPosition) {
        var authors = _chosenFavouriteAuthors.value?.toMutableList()
            ?: mutableListOf()
        authors = authors.filter { authorPair ->
            authorPair.second != position
        }.toMutableList()
        _chosenFavouriteAuthors.postValue(authors)
    }

}