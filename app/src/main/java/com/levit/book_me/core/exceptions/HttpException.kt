package com.levit.book_me.core.exceptions

class HttpException(
    val statusCode: Int,
    val statusMessage: String? = null,
    val url: String? = null,
    val throwable: Throwable? = null
): Exception() {
}