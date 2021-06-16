package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.models.ProfileModel
import kotlinx.coroutines.flow.Flow

interface UserProfileInteractor {

    val profileModel: Flow<ProfileModel>

    suspend fun getProfile()

}