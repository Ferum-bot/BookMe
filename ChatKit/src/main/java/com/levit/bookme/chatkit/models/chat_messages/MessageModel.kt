package com.levit.bookme.chatkit.models.chat_messages

import com.levit.bookme.chatkit.models.enums.MessageStatus
import com.levit.bookme.chatkit.models.enums.MessageType
import java.util.*

interface MessageModel {

    /**
     * The id of message. Needed to correct work of
     * DiffUtil in delegates adapter.
     */
    val id: Long

    /**
     * The Message text.
     */
    val text: String

    /**
     * The Message author
     */
    val author: String

    /**
     * The date-time of current message.
     */
    val date: Date

    /**
     * The author's profile image Url link.
     */
    val authorImageUrlLink: String?

    /**
     * The Message type.
     * @See MessageType
     */
    val type: MessageType

    /**
     * The Message status.
     * @See MessageStatus
     */
    val messageStatus: MessageStatus
}