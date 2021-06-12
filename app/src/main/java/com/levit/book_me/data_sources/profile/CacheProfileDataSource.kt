package com.levit.book_me.data_sources.profile

import com.levit.book_me.core.models.ProfileModel
import kotlinx.coroutines.flow.SharedFlow

interface CacheProfileDataSource {

    val profile: SharedFlow<ProfileModel>

    suspend fun getProfile()

    suspend fun safeProfile(profile: ProfileModel)
}