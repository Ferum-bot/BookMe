package com.levit.book_me.di.modules.network

import com.levit.book_me.network.utill.NetworkConstants
import com.levit.book_me.network.interceptors.ErrorConnectionInterceptor
import com.levit.book_me.network.result_call_adapter.RetrofitResultCallAdapterFactory
import com.levit.book_me.network.utill.TimberHttpLogger
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module(includes = [
    InterceptorsModule::class,
    RetrofitServiceModule::class,
    CoroutineContextModule::class,
    RetrofitModule::class,
    ClientModule::class,
])
open class NetworkModule {

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
