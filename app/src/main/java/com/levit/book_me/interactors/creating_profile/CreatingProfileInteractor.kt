package com.levit.book_me.interactors.creating_profile

import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import kotlinx.coroutines.flow.Flow

interface CreatingProfileInteractor {

    val resultStatus: Flow<RetrofitResult<UserResponseModel>>

    suspend fun uploadNewProfile(profile: ProfileModel)

}