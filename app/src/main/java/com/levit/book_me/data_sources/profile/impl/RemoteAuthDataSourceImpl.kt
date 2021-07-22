package com.levit.book_me.data_sources.profile.impl

import com.levit.book_me.core.models.profile.AuthInfo
import com.levit.book_me.data_sources.profile.RemoteAuthDataSource
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(

): RemoteAuthDataSource {

    override val authInfo: SharedFlow<RetrofitResult<AuthInfo>>
        get() = TODO("Not yet implemented")

    override suspend fun getAuthInfo() {
        TODO("Not yet implemented")
    }

    override suspend fun updatePushToken(newPushToken: String) {
        TODO("Not yet implemented")
    }
}