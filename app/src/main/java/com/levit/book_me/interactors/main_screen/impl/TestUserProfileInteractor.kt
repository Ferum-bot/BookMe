package com.levit.book_me.interactors.main_screen.impl

import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestUserProfileInteractor @Inject constructor(
    private val dataSource: CacheProfileDataSource
): UserProfileInteractor {

    override val profileModel: Flow<ProfileModel> = dataSource.profile

    override suspend fun getProfile() {
        dataSource.getProfile()
    }

}