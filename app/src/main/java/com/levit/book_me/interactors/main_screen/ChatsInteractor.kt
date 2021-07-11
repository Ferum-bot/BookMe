package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.models.chat_kit.UserChat
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow

interface ChatsInteractor {

    val userChats: Flow<BaseRepositoryResult<List<UserChat>>>

    suspend fun getAllChats()
}