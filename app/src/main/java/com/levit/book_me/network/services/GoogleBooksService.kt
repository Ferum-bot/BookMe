package com.levit.book_me.network.services

import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.GoogleBooksResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.QueryName

interface GoogleBooksService {

    @GET("volumes")
    suspend fun searchGoogleBooks(
        @QueryMap
        parameters: Map<String, String>
    ): RetrofitResult<GoogleBooksResponse>

}