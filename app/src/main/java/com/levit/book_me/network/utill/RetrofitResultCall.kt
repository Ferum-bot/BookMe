package com.levit.book_me.network.utill

import com.levit.book_me.core.exceptions.ConnectException
import com.levit.book_me.network.network_result_data.RetrofitResult
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

internal class RetrofitResultCall<T>(proxy: Call<T>): CallDelegate<T, RetrofitResult<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<RetrofitResult<T>>)
    = proxy.enqueue(ResultCallback(this, callback))

    override fun cloneImpl(): Call<RetrofitResult<T>> = RetrofitResultCall(proxy.clone())

    override fun timeout(): Timeout = proxy.timeout()

    private class ResultCallback<T>(
        private val proxy: RetrofitResultCall<T>,
        private val callback: Callback<RetrofitResult<T>>
    ): Callback<T> {

        /**
         * Cast is checked in {responseIsSuccessful()} function
         */
        @Suppress("UNCHECKED_CAST", "ThrowableNotThrown")
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val url = call.request().url().toString()
            val result: RetrofitResult<T> =
            if (responseIsSuccessful(response)) {
                RetrofitResult.Success.HttpResponse(
                    data = response.body() as T,
                    statusCode = response.code(),
                    statusMessage = response.message(),
                    queryUrl = url,
                )
            }
            else {
                RetrofitResult.Failure.HttpError(com.levit.book_me.core.exceptions.HttpException(
                    statusCode = response.code(),
                    statusMessage = response.message(),
                    url = url,
                ))
            }

            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val url = call.request().url().toString()
            val result: RetrofitResult<T> = when(error) {
                is HttpException -> RetrofitResult.Failure.HttpError(com.levit.book_me.core.exceptions.HttpException(
                    statusCode = error.code(),
                    statusMessage = error.message(),
                    url = url,
                    throwable = error,
                ))
                is ConnectException -> RetrofitResult.Failure.Error(
                    error,
                    messageId = error.stringId
                )
                is IOException -> RetrofitResult.Failure.Error(error)
                else -> RetrofitResult.Failure.Error(error)
            }

            callback.onResponse(proxy, Response.success(result))
        }

        private fun responseIsSuccessful(response: Response<T>): Boolean
        = response.isSuccessful && response.body() != null
    }
}