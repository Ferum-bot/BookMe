package com.levit.book_me.di.modules.network

import com.levit.book_me.network.utill.NetworkConstants
import com.levit.book_me.network.interceptors.ErrorConnectionInterceptor
import com.levit.book_me.network.utill.RetrofitResultCallAdapterFactory
import com.levit.book_me.network.utill.TimberHttpLogger
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

@Module(includes = [
    InterceptorsModule::class,
    RetrofitServiceModule::class,
    CoroutineContextModule::class
])
open class NetworkModule {

    @Provides
    fun provideBaseRetrofit(
        converterFactory: MoshiConverterFactory,
        callAdapterFactory: RetrofitResultCallAdapterFactory,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(callAdapterFactory)
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
    fun provideMoshiConverterFactory(moshi: Moshi) = MoshiConverterFactory.create(moshi)

    @Provides
    fun provideRetrofitResultCallAdapterFactory() = RetrofitResultCallAdapterFactory()

    @Provides
    fun provideTimberLogger() = TimberHttpLogger()

    @Provides
    fun provideMoshi(adapterFactory: KotlinJsonAdapterFactory) = Moshi.Builder()
        .add(adapterFactory)
        .build()

    @Provides
    fun provideKotlinJSONAdapterFactory() = KotlinJsonAdapterFactory()
}
