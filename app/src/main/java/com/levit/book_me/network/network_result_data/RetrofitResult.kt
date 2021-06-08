package com.levit.book_me.network.network_result_data

import androidx.annotation.StringRes
import com.levit.book_me.core.exceptions.HttpException
import com.levit.book_me.network.response_models.HttpResponse

sealed class RetrofitResult<out T> {

    sealed class Success<T>: RetrofitResult<T>() {

        abstract val data: T

        override fun toString(): String = "Success: $data"

        class Value<T>(override val data: T): Success<T>()

        data class HttpResponse<T>(
            override val data: T,
            override val statusCode: Int,
            override val statusMessage: String? = null,
            override val queryUrl: String? = null,
        ): Success<T>(), com.levit.book_me.network.response_models.HttpResponse

        object Empty: Success<Nothing>() {

            override val data: Nothing = error("No Data!")

            override fun toString(): String = "Success"
        }
    }


    sealed class Failure<E: Throwable>(open val error: E? = null): RetrofitResult<Nothing>() {

        override fun toString(): String = "Failure: $error"

        class Error(
            override val error: Throwable,
            val message: String? = null,

            @StringRes
            val messageId: Int? = null,
        ): Failure<Throwable>(error)

        data class HttpError(
            override val error: HttpException,
        ): Failure<HttpException>(error), HttpResponse {

            override val statusCode: Int = error.statusCode

            override val statusMessage: String? = error.statusMessage

            override val queryUrl: String? = error.url
        }
    }
}