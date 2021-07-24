package com.levit.book_me.network.services

import com.levit.book_me.core.models.quote.QuoteAuthorModel
import com.levit.book_me.core.models.quote.QuoteModel
import com.levit.book_me.core.models.quote.QuoteTagModel
import com.levit.book_me.core.models.quote.QuotesMainScreenModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.QuotesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QuotesService {

    @GET("/quotes/mainScreenModel")
    suspend fun getMainScreenModel(
        @Query("quotesCount")
        count: Int,
    ): RetrofitResult<QuotesResponse<QuotesMainScreenModel>>

    @GET("/authors/all")
    suspend fun getAllAuthors(): RetrofitResult<QuotesResponse<List<QuoteAuthorModel>>>

    @GET("/tags/all")
    suspend fun getAllTags(): RetrofitResult<QuotesResponse<List<QuoteTagModel>>>

    @GET("/quotes/withTag")
    suspend fun getAllQuotesByTag(
        @Query("tag")
        tag: String,
    ): RetrofitResult<QuotesResponse<List<QuoteModel>>>

    @GET("/quotes/withAuthor")
    suspend fun getAllQuotesByAuthor(
        @Query("author")
        author: String,
    ): RetrofitResult<QuotesResponse<List<QuoteModel>>>

    @GET("/authors/from")
    suspend fun getAllAuthorsFrom(
        @Query("page")
        page: Int,

        @Query("size")
        size: Int,
    ): RetrofitResult<QuotesResponse<List<QuoteAuthorModel>>>

    @GET("/authors/searchFrom")
    suspend fun searchAuthorsFrom(
        @Query("text")
        withText: String,

        @Query("page")
        page: Int,

        @Query("size")
        size: Int,
    ): RetrofitResult<QuotesResponse<List<QuoteAuthorModel>>>
}