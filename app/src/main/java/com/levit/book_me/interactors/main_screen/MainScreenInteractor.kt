package com.levit.book_me.interactors.main_screen

import com.levit.book_me.core.models.chat_kit.Message
import com.levit.book_me.core.models.profile.ProfileModel
import kotlinx.coroutines.flow.Flow

interface MainScreenInteractor {

    val inComingMessages: Flow<Message>

    val searchingInterlocutor: Flow<ProfileModel>

    suspend fun searchNewFriend()

    suspend fun startListeningAllChats()

    suspend fun startListeningChat(id: Long)

    suspend fun stopListeningChat(id: Long)

    fun stopListeningAllChats()
}