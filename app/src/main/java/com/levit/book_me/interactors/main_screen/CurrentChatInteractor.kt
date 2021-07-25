package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.enums.sockets.SocketConnectionStatus
import com.levit.book_me.core.models.chat_kit.Message
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow

interface CurrentChatInteractor {

    val messages: Flow<BaseRepositoryResult<List<Message>>>

    val interlocutor: Flow<BaseRepositoryResult<ProfileModel>>

    val isInterlocutorOnline: Flow<Boolean>

    val isInterlocutorTyping: Flow<Boolean>

    val currentConnectionStatus: Flow<SocketConnectionStatus>

    suspend fun sendMessage(text: String)

    suspend fun retrySendingMessage(message: Message)

    suspend fun getAllMessages()

    suspend fun startListeningSocket(chatId: Long)

    suspend fun reconnectToSocket()

    fun stopListeningSocket()
}