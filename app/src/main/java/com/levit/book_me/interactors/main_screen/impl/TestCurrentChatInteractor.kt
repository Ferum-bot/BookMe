package com.levit.book_me.interactors.main_screen.impl

import com.levit.book_me.core.enums.sockets.SocketConnectionStatus
import com.levit.book_me.core.models.chat_kit.Message
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.interactors.main_screen.CurrentChatInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class TestCurrentChatInteractor : CurrentChatInteractor {

    override val currentConnectionStatus: Flow<SocketConnectionStatus>
        get() = flow { emit(SocketConnectionStatus.CONNECTED) }

    override val interlocutor: Flow<BaseRepositoryResult<ProfileModel>>
        get() = flow {

        }

    override val isInterlocutorOnline: Flow<Boolean>
        get() = flow { emit(true) }

    override val isInterlocutorTyping: Flow<Boolean>
        get() = flow { emit(true) }

    override val messages: Flow<BaseRepositoryResult<List<Message>>> = flow {
        val messagesList = listOf(
            Message(0, 0, 0, "Matvey Popov",
                "https://images.unsplash.com/photo-1623413487006-54669f337144?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
                false,
                "Hello bro! My name is Matvey",
                Date().time,
                ""
            )
        )

        val remoteResult = RetrofitResult.Success.Value(messagesList)
        val result = BaseRepositoryResult.RemoteResult(remoteResult)
        delay(2000L)
        emit(result)
    }

    override suspend fun getAllMessages() {
        TODO("Not yet implemented")
    }

    override suspend fun reconnectToSocket() {
        TODO("Not yet implemented")
    }

    override suspend fun retrySendingMessage(message: Message) {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(text: String) {
        TODO("Not yet implemented")
    }

    override suspend fun startListeningSocket(chatId: Long) {
        TODO("Not yet implemented")
    }

    override fun stopListeningSocket() {
        TODO("Not yet implemented")
    }
}