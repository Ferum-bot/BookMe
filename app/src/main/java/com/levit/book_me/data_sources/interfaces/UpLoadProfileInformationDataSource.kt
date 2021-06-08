package com.levit.book_me.data_sources.interfaces

import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.UserModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import kotlinx.coroutines.flow.SharedFlow

interface UpLoadProfileInformationDataSource {

    val result: SharedFlow<RetrofitResult<UserResponseModel>>

    suspend fun createNewUser(profileModel: ProfileModel)

    suspend fun updateUser(userModel: UserModel)

}