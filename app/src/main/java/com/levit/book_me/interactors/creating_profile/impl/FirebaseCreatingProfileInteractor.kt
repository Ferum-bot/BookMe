package com.levit.book_me.interactors.creating_profile.impl

import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.creating_profile.CreatingProfileInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import com.levit.book_me.repositories.profile.RegisterNewUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@CreatingProfileScope
class FirebaseCreatingProfileInteractor @Inject constructor(
    private val repository: RegisterNewUserRepository
): CreatingProfileInteractor {

    override val resultStatus: Flow<RetrofitResult<UserResponseModel>>
        get() = repository.result

    override suspend fun uploadNewProfile(profile: ProfileModel) {
        repository.registerNewUser(profile)
    }
}