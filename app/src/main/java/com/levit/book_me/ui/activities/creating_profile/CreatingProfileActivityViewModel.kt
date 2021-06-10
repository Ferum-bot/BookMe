package com.levit.book_me.ui.activities.creating_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core.enums.SearchBooksTypes
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.ui.custom_view.CreatingProfileAuthorChooser
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.network.models.google_books.GoogleBook
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

    private val _chosenFavouriteBooks: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val chosenFavouriteBooks: LiveData<List<GoogleBook>> = _chosenFavouriteBooks

    private val _chosenWantToReadBooks: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val chosenWantToReadBooks: LiveData<List<GoogleBook>> = _chosenWantToReadBooks

    private val _chosenGenres: MutableLiveData<List<Genre>> = MutableLiveData()
    val chosenGenres: LiveData<List<Genre>> = _chosenGenres

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

    fun addChosenBook(type: SearchBooksTypes, book: GoogleBook) {
        when(type) {
            SearchBooksTypes.FAVOURITE_BOOKS -> addFavouriteBook(book)
            SearchBooksTypes.BOOKS_YOU_WANT_TO_RED -> addWantToReadBook(book)
        }
    }

    fun removeChosenBook(type: SearchBooksTypes, book: GoogleBook) {
        when(type) {
            SearchBooksTypes.FAVOURITE_BOOKS -> removeFavouriteBook(book)
            SearchBooksTypes.BOOKS_YOU_WANT_TO_RED -> removeWantToReadBook(book)
        }
    }

    fun safeChosenGenres(genres: List<Genre>) {
        _chosenGenres.postValue(genres)
    }

    private fun addFavouriteBook(book: GoogleBook) {
        val books = _chosenFavouriteBooks.value?.toMutableList() ?: mutableListOf()
        books.add(0, book)
        _chosenFavouriteBooks.postValue(books)
    }

    private fun removeFavouriteBook(book: GoogleBook) {
        val books = _chosenFavouriteBooks.value?.toMutableList() ?: mutableListOf()
        books.remove(book)
        _chosenFavouriteBooks.postValue(books)
    }

    private fun addWantToReadBook(book: GoogleBook) {
        val books = _chosenWantToReadBooks.value?.toMutableList() ?: mutableListOf()
        books.add(0, book)
        _chosenWantToReadBooks.postValue(books)
    }

    private fun removeWantToReadBook(book: GoogleBook) {
        val books = _chosenWantToReadBooks.value?.toMutableList() ?: mutableListOf()
        books.remove(book)
        _chosenWantToReadBooks.postValue(books)
    }
}