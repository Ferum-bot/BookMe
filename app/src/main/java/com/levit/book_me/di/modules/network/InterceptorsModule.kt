package com.levit.book_me.di.modules.network

import com.levit.book_me.BuildConfig
import com.levit.book_me.network.interceptors.ErrorConnectionInterceptor
import com.levit.book_me.network.interceptors.HeadersInterceptor
import com.levit.book_me.network.utill.TimberHttpLogger
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

@Module
class InterceptorsModule {

    @Provides
    fun provideHTTPLoggingInterceptor(logger: TimberHttpLogger)
    = HttpLoggingInterceptor(logger).apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    fun provideErrorConnectionInterceptor() = ErrorConnectionInterceptor()

    @Provides
    fun provideHeaderInterceptor() = HeadersInterceptor()
}