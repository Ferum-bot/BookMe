package com.levit.book_me.repositories.sockets

import com.levit.book_me.core.enums.sockets.SocketConnectionStatus
import com.levit.book_me.core.enums.sockets.GeneralEvents
import com.levit.book_me.core.models.chat_kit.Message
import kotlinx.coroutines.flow.SharedFlow

interface GeneralSocketRepository {

    val currentStatus: SharedFlow<SocketConnectionStatus>

    val inComingMessages: SharedFlow<Message>

    suspend fun connectToServer()

    suspend fun startListeningAllEvents()

    suspend fun startListeningEvent(event: GeneralEvents)

    suspend fun stopListeningEvent(event: GeneralEvents)

    suspend fun tryToReconnect()

    suspend fun disconnectFromServer()
}