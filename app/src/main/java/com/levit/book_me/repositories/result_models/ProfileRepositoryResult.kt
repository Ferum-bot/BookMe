package com.levit.book_me.repositories.result_models

import com.levit.book_me.network.network_result_data.RetrofitResult

sealed class ProfileRepositoryResult<out T> {

    open val exception: Throwable? = null

    open val errorMessage: String? = null

    open val errorMessageId: Int? = null

    open val statusCode: Int? = null

    class RemoteResult<T>(val result: RetrofitResult<T>): ProfileRepositoryResult<T>() {

        override val exception: Throwable?
        get() {
            if (result !is RetrofitResult.Failure<*>) {
                return null
            }

            return result.error
        }

        override val errorMessage: String?
        get() {
            if (result !is RetrofitResult.Failure<*>) {
                return null
            }

            return when(result) {
                is RetrofitResult.Failure.Error -> {
                    result.message
                }
                is RetrofitResult.Failure.HttpError -> {
                    result.statusMessage
                }
            }
        }

        override val errorMessageId: Int?
        get() {
            if (result !is RetrofitResult.Failure<*>) {
                return null
            }

            return if (result is RetrofitResult.Failure.Error) {
                result.messageId
            } else {
                null
            }
        }

        override val statusCode: Int?
        get() {
            if (result !is RetrofitResult.Failure<*>) {
                return null
            }

            return if (result is RetrofitResult.Failure.HttpError) {
                result.statusCode
            } else {
                null
            }
        }
    }

    class CacheResult<T>(
        val result: T,

        override val exception: Throwable?,
        override val errorMessage: String?,
        override val errorMessageId: Int?,
        override val statusCode: Int?,

    ): ProfileRepositoryResult<T>()

    object EmptyProfile: ProfileRepositoryResult<Nothing>()
}
