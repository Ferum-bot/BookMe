package com.levit.book_me.network.interceptors

import com.levit.book_me.R
import com.levit.book_me.core.exceptions.ConnectException
import com.levit.book_me.core_network.model.NetworkMonitor
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class ErrorConnectionInterceptor: Interceptor {

    private val connectionIsNotAvailable
    get() = !NetworkMonitor.isNetworkAvailable

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (connectionIsNotAvailable) {
            throw ConnectException(R.string.network_not_available)
        }
        else {
            return chain.proceed(request)
        }
    }
}