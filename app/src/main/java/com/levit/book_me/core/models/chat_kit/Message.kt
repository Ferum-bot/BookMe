package com.levit.book_me.core.models.chat_kit

import android.os.Parcelable
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.models.enums.MessageStatus
import com.levit.bookme.chatkit.models.enums.MessageType
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

import java.util.*

@Parcelize
data class Message(

    @Json(name = "messageId")
    val messageId: Long,

    /**
     * -1 if author is current user.
     */
    @Json(name = "messageAuthorId")
    val messageAuthorId: Long,

    @Json(name = "messageChatId")
    val messageChatId: Long,

    @Json(name = "messageAuthorName")
    val messageAuthorName: String,

    @Json(name = "messageAuthorImageLink")
    val messageAuthorImageLink: String,

    @Json(name = "isMessageFromUser")
    val isMessageFromUser: Boolean,

    @Json(name = "messageText")
    val messageText: String,

    @Json(name = "messageDateMillis")
    val messageDate: Long,

    @Json(name = "messageStatus")
    val currentStatus: String,
): MessageModel, Parcelable {

    override val id: Long
        get() = messageId

    override val author: String
        get() = messageAuthorName

    override val authorImageUrlLink: String?
        get() = messageAuthorImageLink

    override val text: String
        get() = messageText

    override val date: Date
        get() = Date(messageDate)

    override val type: MessageType
        get() = if (isMessageFromUser) {
            MessageType.YOUR_MESSAGE
        } else {
            MessageType.INTERLOCUTOR_MESSAGE
        }

    override val messageStatus: MessageStatus
        get() = MessageStatus.SENT
}
