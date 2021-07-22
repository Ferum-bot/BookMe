package com.levit.book_me.repositories.profile

import com.levit.book_me.core.models.profile.AuthInfo
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.SharedFlow

/**
 * Contains user UUID and device push_token.
 */
interface AuthRepository {

    val authInfo: SharedFlow<BaseRepositoryResult<AuthInfo>>

    suspend fun getAuth()

    suspend fun updateLocalPushToken(newToken: String)

    suspend fun updateRemotePushToken(newToken: String): RetrofitResult<AuthInfo>
}