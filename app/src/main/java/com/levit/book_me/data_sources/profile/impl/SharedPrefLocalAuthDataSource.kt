package com.levit.book_me.data_sources.profile.impl

import android.content.SharedPreferences
import com.levit.book_me.core.models.profile.AuthInfo
import com.levit.book_me.data_sources.profile.LocalAuthDataSource
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class SharedPrefLocalAuthDataSource @Inject constructor(
    private val storage: SharedPreferences,
): LocalAuthDataSource {

    override val authInfo: SharedFlow<AuthInfo>
        get() = TODO("Not yet implemented")


    override suspend fun updateAuthInfo(newAuthInfo: AuthInfo) {
        TODO("Not yet implemented")
    }

    override suspend fun getAuthInfo() {
        TODO("Not yet implemented")
    }

    override suspend fun updatePushToken(newToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUUID(newUUID: String) {
        TODO("Not yet implemented")
    }

}