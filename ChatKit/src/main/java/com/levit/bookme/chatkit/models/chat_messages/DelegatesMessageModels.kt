package com.levit.bookme.chatkit.models.chat_messages

import com.levit.bookme.chatkit.models.enums.MessageType
import java.util.*

internal data class YourMessageModel(
    val messageModel: MessageModel
): MessageModel {

    override val id: Long
    get() = messageModel.id

    override val author: String
    get() = messageModel.author

    override val authorImageUrlLink: String?
    get() = messageModel.authorImageUrlLink

    override val text: String
    get() = messageModel.text

    override val date: Date
    get() = messageModel.date

    override val type: MessageType
    get() = messageModel.type

}

internal data class InterlocutorMessageModel(
    val messageModel: MessageModel
): MessageModel {

    override val id: Long
    get() = messageModel.id

    override val author: String
    get() = messageModel.author

    override val authorImageUrlLink: String?
    get() = messageModel.authorImageUrlLink

    override val text: String
    get() = messageModel.text

    override val date: Date
    get() = messageModel.date

    override val type: MessageType
    get() = messageModel.type

}