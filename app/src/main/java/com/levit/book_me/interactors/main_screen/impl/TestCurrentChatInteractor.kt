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
        get() = flow {
            delay(6000L)
            emit(SocketConnectionStatus.CONNECTED)
        }

    override val interlocutor: Flow<BaseRepositoryResult<ProfileModel>>
        get() = flow {
            val profile = ProfileModel(
                name = "Matvey",
                surname = "Popov",
                profilePhotoUrl = "https://images.unsplash.com/photo-1623413487006-54669f337144?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
            )

            val remoteResult = RetrofitResult.Success.Value(profile)
            val result = BaseRepositoryResult.RemoteResult(remoteResult)
            delay(2000L)
            emit(result)
        }

    override val isInterlocutorOnline: Flow<Boolean>
        get() = flow { emit(true) }

    override val isInterlocutorTyping: Flow<Boolean>
        get() = flow {
            delay(6000L)
            emit(true)
        }

    override val messages: Flow<BaseRepositoryResult<List<Message>>> = flow {
        val messagesList = listOf(
            Message(0, 0, 0, "Matvey Popov",
                "https://images.unsplash.com/photo-1623413487006-54669f337144?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
                false,
                "Hello bro! My name is Matvey",
                Date().time - 60000,
                ""
            ),
            Message(1, -1, 0, "You",
                "https://images.unsplash.com/photo-1623512335584-a7d391d8bf82?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
                true,
                "Hello! How are you",
                Date().time - 50000,
                ""
            ),
            Message(2, 0, 0, "Matvey Popov",
                "https://images.unsplash.com/photo-1623413487006-54669f337144?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
                false,
                "I am fine thanks and you?",
                Date().time - 40000,
                ""
            ),
            Message(3, 0, 0, "Matvey Popov",
                "https://images.unsplash.com/photo-1623413487006-54669f337144?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
                false,
                "Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.Long message test test test long message.",
                Date().time - 30000,
                ""
            ),
            Message(4, 0, 0, "You",
                "https://images.unsplash.com/photo-1623512335584-a7d391d8bf82?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
                true,
                "Year, sounds greate! Let's meet at 23:53 AM!",
                Date().time - 20000,
                ""
            ),
        )

        val remoteResult = RetrofitResult.Success.Value(messagesList)
        val result = BaseRepositoryResult.RemoteResult(remoteResult)
        delay(4000L)
        emit(result)
    }

    override suspend fun getAllMessages() {

    }

    override suspend fun reconnectToSocket() {

    }

    override suspend fun retrySendingMessage(message: Message) {

    }

    override suspend fun sendMessage(text: String) {

    }

    override suspend fun startListeningSocket(chatId: Long) {

    }

    override fun stopListeningSocket() {
        
    }
}