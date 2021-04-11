package com.levit.book_me.network.services

import com.levit.book_me.network.response_models.GoogleBooksResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.QueryName

interface GoogleBooksService {

    @GET("volumes")
    fun searchGoogleBooks(
        @QueryMap
        parameters: Map<String, String>
    ): GoogleBooksResponse

}