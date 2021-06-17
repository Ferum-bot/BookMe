package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.interactors.creating_profile.UploadProfileImageInteractor
import kotlinx.coroutines.flow.Flow

interface UserProfileInteractor: UploadProfileImageInteractor {

    val profileModel: Flow<ProfileModel>

    suspend fun getProfile()

}