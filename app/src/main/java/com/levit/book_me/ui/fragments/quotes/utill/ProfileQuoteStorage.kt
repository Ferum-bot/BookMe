package com.levit.book_me.ui.fragments.quotes.utill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.core.models.quote.GoQuote

/**
 * I know that is not good code. So I
 * will remove it lately.
 */
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