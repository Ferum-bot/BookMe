package com.levit.book_me.interactors.creating_profile.impl

import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.interactors.creating_profile.CreatingProfileInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.network.response_models.user.UserResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheCreatingProfileInteractor @Inject constructor(
    private val localDataSource: CacheProfileDataSource
): CreatingProfileInteractor {

    companion object {

        private const val DELAY_TIME = 1000L
    }

    private val _profile: SharedFlow<ProfileModel> = localDataSource.profile
        .onEach {
            val model = it
        }
        .shareIn(CoroutineScope(Dispatchers.IO), SharingStarted.Eagerly)
    override val resultStatus: Flow<RetrofitResult<UserResponseModel>> = flow {
        val resultData = UserResponseModel(
            statusMessage = "Success OK",
            statusCode = 200,
        )
        val result = RetrofitResult.Success.Value(resultData)
        //emit(result)
    }

    override suspend fun uploadNewProfile(profile: ProfileModel) {
        withContext(Dispatchers.IO) {
            localDataSource.safeProfile(profile)
            delay(DELAY_TIME)
            localDataSource.getProfile()
//            localDataSource.profile.collect { profile ->
//                println(profile)
//            }
//            localDataSource.getProfile()
        }
    }
}