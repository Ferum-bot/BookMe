package com.levit.book_me.core.models

data class GoQuotesParameters(
    val type: GoQuotesTypes,
    val valueToSearch: String,
    val resultCount: Int = DEFAULT_COUNT
) {
    companion object {
        private const val DEFAULT_COUNT = 50
    }
}

enum class GoQuotesTypes(val string: String) {
    TAG("tag"), AUTHOR("author")
}
