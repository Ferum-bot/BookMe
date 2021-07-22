package com.levit.book_me.data_sources.profile

import com.levit.book_me.core.models.profile.AuthInfo
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow

interface RemoteAuthDataSource {

    val authInfo: SharedFlow<RetrofitResult<AuthInfo>>

    suspend fun getAuthInfo()

    suspend fun updatePushToken(newPushToken: String)
}