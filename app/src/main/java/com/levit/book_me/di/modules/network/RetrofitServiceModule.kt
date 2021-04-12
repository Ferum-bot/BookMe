package com.levit.book_me.di.modules.network

import com.levit.book_me.network.services.GoogleBooksService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitServiceModule {

    @Provides
    @Singleton
    fun provideGoogleBooksService(retrofit: Retrofit): GoogleBooksService
            = retrofit.create(GoogleBooksService::class.java)

}