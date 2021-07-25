package com.levit.book_me.ui.fragments.quotes.utill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.core.models.quote.QuoteModel

/**
 * I know that is not good code. So I
 * will remove it lately.
 */
object ProfileQuoteStorage {

    private val _quote: MutableLiveData<QuoteModel?> = MutableLiveData(null)
    val quote: LiveData<QuoteModel?> = _quote

    fun setQuote(quote: QuoteModel) {
        _quote.postValue(quote)
    }

    fun removeQuote() {
        _quote.postValue(null)
    }
}