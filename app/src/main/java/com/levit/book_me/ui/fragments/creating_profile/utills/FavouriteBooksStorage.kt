package com.levit.book_me.ui.fragments.creating_profile.utills

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.network.models.google_books.GoogleBook

/**
 * Temporary solution to pass chosen favourite books
 * between fragments.
 * Remove this lately. Rewrite to shared ViewModel.
 */
object FavouriteBooksStorage {

    private val _books: MutableLiveData<List<GoogleBook>> = MutableLiveData()
    val books: LiveData<List<GoogleBook>> = _books

    fun setBooks(books: List<GoogleBook>) {
        _books.postValue(books)
    }
}