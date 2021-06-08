package com.levit.book_me.di.modules.network

import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.services.GoQuotesService
import com.levit.book_me.network.services.GoogleBooksService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class RetrofitServiceModule {

    @Provides
    fun provideGoogleBooksService(
        @Named(DIConstants.GOOGLE_BOOK_RETROFIT_NAME)
        retrofit: Retrofit,
    ): GoogleBooksService
        = retrofit.create(GoogleBooksService::class.java)

    @Provides
    fun provideGoQuotesService(
        @Named(DIConstants.GO_QUOTES_RETROFIT_NAME)
        retrofit: Retrofit
    ): GoQuotesService
        = retrofit.create(GoQuotesService::class.java)
}