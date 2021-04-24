package com.levit.book_me.core.models

data class QuotesMainScreenModel(
    val numberOfTags: Int,
    val numberOfAuthors: Int,

    val randomQuotes: List<GoQuote>
)