package com.levit.book_me.repositories.profile.impl

import com.levit.book_me.core.models.profile.AuthInfo
import com.levit.book_me.data_sources.profile.LocalAuthDataSource
import com.levit.book_me.data_sources.profile.RemoteAuthDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.profile.AuthRepository
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class AuthRepositoryImpl @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineScope: CoroutineContext,

    private val remoteDataSource: RemoteAuthDataSource,
    private val localDataSource: LocalAuthDataSource,
): AuthRepository {

    override val authInfo: SharedFlow<BaseRepositoryResult<AuthInfo>>
        get() = TODO("Not yet implemented")

    override suspend fun getAuth() {
        TODO("Not yet implemented")
    }

    override suspend fun updateLocalPushToken(newToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateRemotePushToken(newToken: String): RetrofitResult<AuthInfo> {
        TODO("Not yet implemented")
    }
}