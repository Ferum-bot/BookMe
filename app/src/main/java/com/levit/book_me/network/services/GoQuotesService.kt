package com.levit.book_me.network.services

import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.go_quotes.GoQuotesAuthorsResponse
import com.levit.book_me.network.response_models.go_quotes.GoQuotesQuoteResponse
import com.levit.book_me.network.response_models.go_quotes.GoQuotesTagsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoQuotesService {


    @GET("random")
    suspend fun getRandomQuotes(
        @Query("count")
        count: Int
    ): RetrofitResult<GoQuotesQuoteResponse>

    @GET("random/{count}")
    suspend fun getRandomQuotesWithFilter(
        @Path("count")
        count: Int,

        @Query("type")
        type: String,

        @Query("val")
        typeValue: String,
    ): RetrofitResult<GoQuotesQuoteResponse>

    @GET("all/authors")
    suspend fun getAllQuotesAuthors(): RetrofitResult<GoQuotesAuthorsResponse>

    @GET("all/tags")
    suspend fun getAllQuotesTags(): RetrofitResult<GoQuotesTagsResponse>

    @GET("all")
    suspend fun getAllQuotesWithFilter(
        @Query("type")
        type: String,

        @Query("val")
        typeValue: String,
    ): RetrofitResult<GoQuotesQuoteResponse>


}