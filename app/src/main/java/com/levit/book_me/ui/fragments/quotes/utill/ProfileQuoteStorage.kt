package com.levit.book_me.ui.fragments.quotes.utill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.core.models.GoQuote

object ProfileQuoteStorage {

    private val _quote: MutableLiveData<GoQuote?> = MutableLiveData(null)
    val quote: LiveData<GoQuote?> = _quote

    fun setQuote(quote: GoQuote) {
        _quote.postValue(quote)
    }

    fun removeQuote() {
        _quote.postValue(null)
    }
}