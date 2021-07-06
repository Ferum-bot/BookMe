package com.levit.bookme.chatkit.models.interfaces

import com.levit.bookme.chatkit.models.enums.MessageType
import java.util.*

interface MessageModel {

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

}