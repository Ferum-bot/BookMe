package com.levit.book_me.network.utill

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.time.Duration
import kotlin.time.DurationUnit

object NetworkConstants {

    const val NETWORK_CONNECTION_TIMEOUT_SECONDS = 7L
    const val NETWORK_CALL_TIMEOUT_SECONDS = 7L
    const val NETWORK_READ_TIMEOUT_SECONDS = 7L
    const val NETWORK_WRITE_TIMEOUT_SECONDS = 7L

    const val QUOTES_NETWORK_CONNECTION_TIMEOUT_SECONDS = 15L
    const val QUOTES_NETWORK_CALL_TIMEOUT_SECONDS = 15L
    const val QUOTES_NETWORK_READ_TIMEOUT_SECONDS = 15L
    const val QUOTES_NETWORK_WRITE_TIMEOUT_SECONDS = 15L

    const val GOOGLE_BOOKS_API_BASE_URL = "https://www.googleapis.com/books/v1/"
    const val QUOTES_API_BASE_URL = "https://bookme777.herokuapp.com/api/v1/"

    const val POPULAR_GOOGLE_BOOKS_ID = "100776151416000974307"
    const val MOST_CHOSEN_GOOGLE_BOOKS_ID = "100776151416000974307"

    const val POPULAR_GOOGLE_SHELF_ID = "0"
    const val MOST_CHOSEN_GOOGLE_SHELF_ID = "2"

    const val GOOGLE_BOOKS_API_KEY = "AIzaSyDPkHJKI-bsfrUxKee6y8_88O8JnaqooA8"

    const val PLATFORM_HEADER_NAME = "Platform"
    const val PLATFORM_HEADER_VALUE = "Android"

    const val QUOTES_SECRET_KEY_NAME = "Secret-Key"
    const val QUOTES_SECRET_KEY_VALUE = "TryToBootTrapIt!"
}