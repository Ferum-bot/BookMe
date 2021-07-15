package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.models.chat_kit.Message
import kotlinx.coroutines.flow.Flow

interface MainScreenInteractor {

    val inComingMessages: Flow<Message>

    suspend fun startListeningAllChats()

    suspend fun startListeningChat(id: Long)

    suspend fun stopListeningChat(id: Long)

}