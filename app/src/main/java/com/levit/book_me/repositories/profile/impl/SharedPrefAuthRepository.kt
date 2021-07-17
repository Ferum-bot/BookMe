package com.levit.book_me.repositories.profile.impl

import android.content.SharedPreferences
import com.levit.book_me.core.models.profile.AuthInfo
import com.levit.book_me.di.DIConstants
import com.levit.book_me.repositories.profile.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class SharedPrefAuthRepository @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineScope: CoroutineContext,

    @Named(DIConstants.AUTH_INFO_SHARED_PREFERENCE_NAME)
    private val storage: SharedPreferences,
): AuthRepository {

    override val authInfo: SharedFlow<AuthInfo>
        get() = TODO("Not yet implemented")

    override suspend fun getAuth() {
        TODO("Not yet implemented")
    }

    override suspend fun updatePushToken(newToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUUID(newUUID: String) {
        TODO("Not yet implemented")
    }
}