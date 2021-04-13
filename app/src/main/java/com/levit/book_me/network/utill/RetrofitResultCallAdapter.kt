package com.levit.book_me.network.utill

import com.levit.book_me.network.network_result_data.RetrofitResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RetrofitResultCallAdapter<R>(
    private val type: Type
): CallAdapter<R, Call<RetrofitResult<R>>> {

    override fun adapt(call: Call<R>): Call<RetrofitResult<R>> = RetrofitResultCall(call)

    override fun responseType(): Type = type
}