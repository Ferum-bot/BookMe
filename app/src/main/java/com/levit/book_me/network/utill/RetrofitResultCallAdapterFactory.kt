package com.levit.book_me.network.utill

import com.levit.book_me.network.network_result_data.RetrofitResult
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RetrofitResultCallAdapterFactory: CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val rawReturnType: Class<*> = getRawType(returnType)

        return if (rawReturnType == Call::class.java) {
            getParameterizedType(returnType)
        }
        else {
            null
        }
    }

    private fun getParameterizedType(returnType: Type): CallAdapter<*, *>? =
    if (returnType is ParameterizedType) {
        val callInnerType = getParameterUpperBound(0, returnType)
        getRetrofitResultCallAdapter(callInnerType)
    }
    else {
        null
    }

    private fun getRetrofitResultCallAdapter(callInnerType: Type): CallAdapter<*, *>? =
    if (getRawType(callInnerType) == RetrofitResult::class.java) {
        if (callInnerType is ParameterizedType) {
            val resultInnerType = getParameterUpperBound(0, callInnerType)
            RetrofitResultCallAdapter<Any?>(resultInnerType)
        } else {
            RetrofitResultCallAdapter<Nothing>(Nothing::class.java)
        }
    }
    else {
        null
    }
}