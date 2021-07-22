package com.levit.book_me.data_sources.profile

import com.levit.book_me.core.models.profile.AuthInfo
import kotlinx.coroutines.flow.SharedFlow

interface LocalAuthDataSource {

    val authInfo: SharedFlow<AuthInfo>

    suspend fun getAuthInfo()

    suspend fun updateAuthInfo(newAuthInfo: AuthInfo)

    suspend fun updatePushToken(newToken: String)

    suspend fun updateUUID(newUUID: String)

}