package com.levit.book_me.core.db.converters.list

class AuthorIdsListConverter : BaseListConverter<Long>() {
    override fun convertStringToValue(string: String) = string.toLong()
}
