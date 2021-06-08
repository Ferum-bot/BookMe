package com.levit.book_me.network.exceptions

import com.levit.book_me.network.models.google_books.GoogleBooksError
import com.levit.book_me.network.response_models.HttpResponse

class GoogleBooksException(
    override val statusCode: Int,
    override val statusMessage: String?,
    override val queryUrl: String?,

    val errorMessage: String?,
    val errors: List<GoogleBooksError>?
): Exception(), HttpResponse