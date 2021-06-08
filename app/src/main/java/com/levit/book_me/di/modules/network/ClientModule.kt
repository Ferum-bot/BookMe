package com.levit.book_me.di.modules.network

import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.interceptors.ErrorConnectionInterceptor
import com.levit.book_me.network.utill.NetworkConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
open class ClientModule {

    @Named(DIConstants.GOOGLE_BOOK_CLIENT_NAME)
    @Provides
    fun provideGoogleBooksClient(
        loggingInterceptor: HttpLoggingInterceptor,
        connectionInterceptor: ErrorConnectionInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(connectionInterceptor)
        .connectTimeout(NetworkConstants.NETWORK_CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .callTimeout(NetworkConstants.NETWORK_CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(NetworkConstants.NETWORK_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(NetworkConstants.NETWORK_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    @Named(DIConstants.GO_QUOTES_CLIENT_NAME)
    @Provides
    fun provideQuotesClient(
        loggingInterceptor: HttpLoggingInterceptor,
        connectionInterceptor: ErrorConnectionInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(connectionInterceptor)
        .connectTimeout(NetworkConstants.QUOTES_NETWORK_CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .callTimeout(NetworkConstants.QUOTES_NETWORK_CALL_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(NetworkConstants.QUOTES_NETWORK_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(NetworkConstants.QUOTES_NETWORK_WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()
}