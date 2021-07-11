package com.levit.book_me.core.models.chat_kit

import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.models.enums.ChatLastMessageFrom
import com.squareup.moshi.Json
import java.util.*

data class UserChat(

    @Json(name = "userChatId")
    val chatId: Long = 0,

    @Json(name = "userInterlocutorId")
    val interlocutorId: Long,

    @Json(name = "userInterlocutorName")
    val interlocutorFullName: String,

    @Json(name = "userInterlocutorProfileImageUrl")
    val interlocutorImageUrl: String,

    @Json(name = "userChatLastMessage")
    val chatLastMessage: String?,

    @Json(name = "userChatLastMessageDateMillis")
    val lastMessageDate: Long?,

    @Json(name = "userChatIsLastMessageFromUser")
    val isLastMessageFromUser: Boolean

): ChatModel {

    override val id: Long
        get() = chatId

    override val interlocutorName: String
        get() = interlocutorFullName

    override val interlocutorProfileImageUrl: String
        get() = interlocutorImageUrl

    override val lastMessage: String?
        get() = chatLastMessage

    override val dateOfLastMessage: Date?
        get() {
            if (lastMessageDate == null) {
                return null
            }
            return Date(lastMessageDate)
        }

    override val lastMessageFrom: ChatLastMessageFrom
        get() = if (isLastMessageFromUser) {
            ChatLastMessageFrom.MESSAGE_FROM_YOU
        } else {
            ChatLastMessageFrom.MESSAGE_FROM_INTERLOCUTOR
        }
}

