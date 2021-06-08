package com.levit.book_me.network.utill

import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class TimberHttpLogger: HttpLoggingInterceptor.Logger {

    companion object {
        private const val LOG_TAG = "OkHttp"
    }

    override fun log(message: String) {
        Timber.tag(LOG_TAG).d(message)
    }
}