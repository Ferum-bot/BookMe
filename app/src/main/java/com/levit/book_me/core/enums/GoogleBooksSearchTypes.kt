package com.levit.book_me.core.enums

enum class GoogleBooksSearchTypes(val queryName: String) {
    SEARCH_IN_TITLE("intitle:"),
    SEARCH_IN_AUTHOR("inauthor:"),
    SEARCH_IN_PUBLISHER("inpublisher:");
}