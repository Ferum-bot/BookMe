package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.models.profile.UserModel
import com.levit.book_me.core.models.chat_kit.Message
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow

interface CurrentChatInteractor {

    val messages: Flow<BaseRepositoryResult<List<Message>>>

    val interlocutor: Flow<BaseRepositoryResult<UserModel>>

    val isInterlocutorOnline: Flow<Boolean>

    val isInterlocutorTyping: Flow<Boolean>

    suspend fun sendMessage()

    suspend fun getAllMessages()

    suspend fun startListeningSocket()

    suspend fun stopListeningSocket()
}