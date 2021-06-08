package com.levit.book_me.network.services

import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.google_books.GoogleBooksResponse
import com.levit.book_me.network.utill.NetworkConstants
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GoogleBooksService {

    @GET("volumes")
    suspend fun searchGoogleBooks(
        @QueryMap
        parameters: Map<String, String>
    ): RetrofitResult<GoogleBooksResponse>

    @GET("users/${NetworkConstants.POPULAR_GOOGLE_BOOKS_ID}/bookshelves/${NetworkConstants.POPULAR_GOOGLE_SHELF_ID}/volumes")
    suspend fun searchPopularBooks(
        @QueryMap
        parameters: Map<String, String>
    ): RetrofitResult<GoogleBooksResponse>

    @GET("users/${NetworkConstants.MOST_CHOSEN_GOOGLE_BOOKS_ID}/bookshelves/${NetworkConstants.MOST_CHOSEN_GOOGLE_SHELF_ID}/volumes")
    suspend fun searchMostChosenBooks(
        @QueryMap
        parameters: Map<String, String>
    ): RetrofitResult<GoogleBooksResponse>
}