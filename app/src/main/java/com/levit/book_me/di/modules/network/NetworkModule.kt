package com.levit.book_me.di.modules.network

import com.levit.book_me.network.utill.NetworkConstants
import com.levit.book_me.network.interceptors.ErrorConnectionInterceptor
import com.levit.book_me.network.services.GoogleBooksService
import com.levit.book_me.network.utill.TimberHttpLogger
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class NetworkModule {

    @Provides
    @Singleton
    fun provideGoogleBooksService(retrofit: Retrofit): GoogleBooksService
    = retrofit.create(GoogleBooksService::class.java)

    @Provides
    fun provideBaseRetrofit(
        converterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .client(client)
        .baseUrl(NetworkConstants.GOOGLE_BOOKS_API_BASE_URL)
        .build()

    @Provides
    fun provideClient(
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

    @Provides
    fun provideJSONConverterFactory() = GsonConverterFactory.create()

    @Provides
    fun provideTimberLogger() = TimberHttpLogger()
}
