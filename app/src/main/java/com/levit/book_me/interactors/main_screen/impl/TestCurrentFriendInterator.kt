package com.levit.book_me.interactors.main_screen.impl

import com.levit.book_me.core.models.FriendProfileModel
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.CurrentFriendInteractor
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@MainScreenScope
class TestCurrentFriendInterator @Inject constructor(

): CurrentFriendInteractor {

    override val friendModel: Flow<BaseRepositoryResult<FriendProfileModel>>
    = flow {

    }

    override suspend fun getFriend(id: Long) {

    }
}