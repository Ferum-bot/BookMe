package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.models.profile.FriendProfileModel
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow

interface CurrentFriendInteractor {

    val friendModel: Flow<BaseRepositoryResult<FriendProfileModel>>

    suspend fun getFriend(id: Long)
}