package com.levit.book_me.repositories.sockets

import com.levit.book_me.core.enums.sockets.CurrentChatEvents
import com.levit.book_me.core.enums.sockets.SocketConnectionStatus
import com.levit.book_me.core.models.chat_kit.Message
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.SharedFlow

interface CurrentChatSocketRepository {

    val currentStatus: SharedFlow<SocketConnectionStatus>

    val messages: SharedFlow<BaseRepositoryResult<List<Message>>>

    val interlocutor: SharedFlow<BaseRepositoryResult<ProfileModel>>

    val chatEvents: SharedFlow<CurrentChatEvents>

    suspend fun connectToChat(chatId: Long)

    suspend fun reconnectToChat()

    suspend fun sendNewMessage(messageText: String)

    suspend fun retryToSendMessage(message: Message)

    fun disconnectFromChat()
}