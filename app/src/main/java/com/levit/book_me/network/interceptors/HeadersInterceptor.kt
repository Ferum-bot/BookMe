package com.levit.book_me.network.interceptors

import com.levit.book_me.network.utill.NetworkConstants
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(NetworkConstants.PLATFORM_HEADER_NAME, NetworkConstants.PLATFORM_HEADER_VALUE)
            .build()

        return chain.proceed(newRequest)
    }

}