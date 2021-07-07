package com.levit.bookme.chatkit.models.interfaces

import com.levit.bookme.chatkit.models.enums.ChatLastMessageFrom
import java.util.*

interface ChatModel {

    /**
     * The name of interlocutor from current chat.
     */
    val interlocutorName: String

    /**
     * The interlocutor profile image link.
     */
    val interlocutorProfileImageUrl: String

    /**
     * The last message if you or your interlocutor.
     *
     * @Important
     * If last message is from you, the message will be
     * with prefix "You: "
     */
    val lastMessage: String?

    /**
     * The flag which shows from whom the last message was received.
     */
    val lastMessageFrom: ChatLastMessageFrom

    /**
     * The date of the last message.
     */
    val dateOfLastMessage: Date?
}