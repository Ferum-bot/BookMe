package com.levit.book_me.network.response_models

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val queryUrl: String?

}