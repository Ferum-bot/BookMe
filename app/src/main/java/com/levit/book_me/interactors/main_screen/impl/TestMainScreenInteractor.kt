package com.levit.book_me.interactors.main_screen.impl

import com.levit.book_me.core.models.chat_kit.Message
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.interactors.main_screen.MainScreenInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestMainScreenInteractor: MainScreenInteractor {

    override val inComingMessages: Flow<Message>
        get() = flow {  }
    override val searchingInterlocutor: Flow<ProfileModel>
        get() = flow {  }

    override suspend fun searchNewFriend() {

    }

    override suspend fun startListeningAllChats() {

    }

    override suspend fun startListeningChat(id: Long) {

    }

    override suspend fun stopListeningChat(id: Long) {

    }

    override fun stopListeningAllChats() {

    }

}