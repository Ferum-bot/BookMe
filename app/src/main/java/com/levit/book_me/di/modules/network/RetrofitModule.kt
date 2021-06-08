package com.levit.book_me.di.modules.network

import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.result_call_adapter.RetrofitResultCallAdapterFactory
import com.levit.book_me.network.utill.NetworkConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
open class RetrofitModule {

    @Named(DIConstants.GOOGLE_BOOK_RETROFIT_NAME)
    @Provides
    fun provideGoogleBooksRetrofit(
        converterFactory: MoshiConverterFactory,
        callAdapterFactory: RetrofitResultCallAdapterFactory,

        @Named(DIConstants.GOOGLE_BOOK_CLIENT_NAME)
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .client(client)
        .baseUrl(NetworkConstants.GOOGLE_BOOKS_API_BASE_URL)
        .build()

    @Named(DIConstants.GO_QUOTES_RETROFIT_NAME)
    @Provides
    fun provideGoQuotesRetrofit(
        converterFactory: MoshiConverterFactory,
        callAdapterFactory: RetrofitResultCallAdapterFactory,

        @Named(DIConstants.GO_QUOTES_CLIENT_NAME)
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(callAdapterFactory)
        .addConverterFactory(converterFactory)
        .client(client)
        .baseUrl(NetworkConstants.GO_QUOTES_API_BASE_URL)
        .build()

}