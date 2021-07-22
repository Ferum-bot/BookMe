package com.levit.book_me.repositories.profile

import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import kotlinx.coroutines.flow.SharedFlow

interface RegisterNewUserRepository {

    val result: SharedFlow<RetrofitResult<UserResponseModel>>

    suspend fun registerNewUser(profileModel: ProfileModel)
}