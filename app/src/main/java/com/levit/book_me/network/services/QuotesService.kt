package com.levit.book_me.network.services

import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.quotes.QuotesAuthorResponse
import com.levit.book_me.network.response_models.quotes.QuotesMainScreenResponse
import com.levit.book_me.network.response_models.quotes.QuotesQuoteResponse
import com.levit.book_me.network.response_models.quotes.QuotesTagResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesService {

    @GET("quotes/mainScreenModel")
    suspend fun getMainScreenModel(
        @Query("quotesCount")
        count: Int,
    ): RetrofitResult<QuotesMainScreenResponse>

    @GET("authors/all")
    suspend fun getAllAuthors(): RetrofitResult<QuotesAuthorResponse>

    @GET("tags/all")
    suspend fun getAllTags(): RetrofitResult<QuotesTagResponse>

    @GET("quotes/withTag")
    suspend fun getAllQuotesByTag(
        @Query("tag")
        tag: String,
    ): RetrofitResult<QuotesQuoteResponse>

    @GET("quotes/withAuthor")
    suspend fun getAllQuotesByAuthor(
        @Query("author")
        author: String,
    ): RetrofitResult<QuotesQuoteResponse>

    @GET("authors/from")
    suspend fun getAllAuthorsFrom(
        @Query("page")
        page: Int,

        @Query("size")
        size: Int,
    ): RetrofitResult<QuotesAuthorResponse>

    @GET("authors/searchFrom")
    suspend fun searchAuthorsFrom(
        @Query("text")
        withText: String,

        @Query("page")
        page: Int,

        @Query("size")
        size: Int,
    ): RetrofitResult<QuotesAuthorResponse>
}