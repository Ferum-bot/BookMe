package com.levit.book_me.core.models.quote

import com.levit.book_me.core.models.quote.GoQuote

data class QuotesMainScreenModel(
    val numberOfTags: Int,
    val numberOfAuthors: Int,

    val randomQuotes: List<GoQuote>
)