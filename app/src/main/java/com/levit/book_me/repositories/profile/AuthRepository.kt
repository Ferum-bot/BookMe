package com.levit.book_me.repositories.profile

import com.levit.book_me.core.models.profile.AuthInfo
import kotlinx.coroutines.flow.SharedFlow

interface AuthRepository {

    val authInfo: SharedFlow<AuthInfo>

    suspend fun getAuth()

    suspend fun updatePushToken(newToken: String)

    suspend fun updateUUID(newUUID: String)
}