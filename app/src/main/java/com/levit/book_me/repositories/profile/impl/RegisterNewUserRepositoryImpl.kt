package com.levit.book_me.repositories.profile.impl

import com.levit.book_me.core.enums.profile.CurrentUserStatus
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.data_sources.profile.RegisterNewUserDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import com.levit.book_me.repositories.profile.RegisterNewUserRepository
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class RegisterNewUserRepositoryImpl @Inject constructor(
    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val coroutineContext: CoroutineContext,

    private val localDataSource: CacheProfileDataSource,
    private val remoteDataSource: RegisterNewUserDataSource,
): RegisterNewUserRepository{

    override val result: SharedFlow<RetrofitResult<UserResponseModel>> = remoteDataSource.resultStatus

    override suspend fun registerNewUser(profileModel: ProfileModel)
    = withContext(coroutineContext)  {
        localDataSource.safeProfile(profileModel)
        localDataSource.setCurrentStatus(CurrentUserStatus.AUTHORIZED_PROFILE_CREATED)
        remoteDataSource.registerNewUser(profileModel)
    }
}