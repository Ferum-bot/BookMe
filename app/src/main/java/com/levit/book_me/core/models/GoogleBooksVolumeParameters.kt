package com.levit.book_me.core.models

import com.levit.book_me.core.enums.GoogleBooksPrintTypes
import com.levit.book_me.core.enums.GoogleBooksSearchTypes
import com.levit.book_me.core.enums.GoogleBooksSortTypes
import com.levit.book_me.network.utill.NetworkConstants

data class GoogleBooksVolumeParameters(
    val textToSearch: String,
    val searchType: GoogleBooksSearchTypes = GoogleBooksSearchTypes.SEARCH_IN_TITLE,
    val pageSize: Int = DEFAULT_PAGE_SIZE,
    val printType: GoogleBooksPrintTypes = GoogleBooksPrintTypes.ALL,
    val sortType: GoogleBooksSortTypes = GoogleBooksSortTypes.RELEVANCE
) {
    companion object {
        private const val API_KEY_NAME = "key"
        private const val SEARCH_NAME = "q"
        private const val PAGE_SIZE_NAME = "maxResults"
        private const val PRINT_TYPE_NAME = "printType"
        private const val SORT_TYPE_NAME = "orderBy"

        private const val DEFAULT_PAGE_SIZE = 25
    }

    private val searchRequest: String
    get() = searchType.queryName + textToSearch

    fun toMap(): Map<String, String> = mapOf(
        API_KEY_NAME to NetworkConstants.GOOGLE_BOOKS_API_KEY,
        SEARCH_NAME to  searchRequest,
        PAGE_SIZE_NAME to pageSize.toString(),
        PRINT_TYPE_NAME to printType.queryName,
        SORT_TYPE_NAME to sortType.queryName,
    )
}