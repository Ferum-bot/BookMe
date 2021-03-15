package com.levit.book_me.core_network.repository

import com.levit.book_me.core_network.caching.CachePolicies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class Call<T>(open var cachePolicy: CachePolicies = CachePolicies.NETWORK_OR_CACHE) {
    abstract suspend fun fromCache(): T?
    abstract suspend fun writeToCache(value: T)
    abstract suspend fun fromNetwork(): T?

    suspend fun await(): T? {
        return when (cachePolicy) {
            CachePolicies.CACHE_OR_NETWORK -> cacheOrNetwork()
            CachePolicies.NETWORK_OR_CACHE -> networkOrCache()
            CachePolicies.ALWAYS_NETWORK -> alwaysNetwork()
            CachePolicies.ALWAYS_CACHE -> alwaysCache()
        }
    }

    suspend fun precache() {
        alwaysNetwork()
    }

    private suspend fun alwaysCache(): T? {
        return fromCache()
    }

    private suspend fun cacheOrNetwork(): T? = withContext(Dispatchers.IO) {
        val cacheModel = fromCache()
        return@withContext if (cacheModel != null) cacheModel else {
            val networkModel = fromNetwork()
            if (networkModel != null) writeToCache(networkModel)
            networkModel
        }
    }

    private suspend fun networkOrCache(): T? = withContext(Dispatchers.IO) {
        val networkModel = fromNetwork()
        return@withContext if (networkModel != null) {
            writeToCache(networkModel)
            networkModel
        } else {
            val cacheModel = fromCache()
            cacheModel
        }
    }

    private suspend fun alwaysNetwork(): T? = withContext(Dispatchers.IO) {
        val result = fromNetwork()
        if (result != null) {
            writeToCache(result)
        }
        return@withContext result
    }
}
