package com.levit.bookme.chatkit.extensions

import com.levit.bookme.chatkit.models.enums.ChatLastMessageFrom
import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.ui.chat.ChatView
import java.util.*

internal fun ChatView.emptyChatModel(): ChatModel {
    return object: ChatModel {
        override val id: Long = 0
        override val interlocutorName: String = "Empty Name"
        override val interlocutorProfileImageUrl: String = ""
        override val lastMessage: String? = null
        override val lastMessageFrom: ChatLastMessageFrom = ChatLastMessageFrom.MESSAGE_FROM_YOU
        override val dateOfLastMessage: Date? = null
        override val numberOfUnreadMessaged: Int = 0
    }
}