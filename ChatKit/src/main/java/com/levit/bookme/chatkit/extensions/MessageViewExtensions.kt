package com.levit.bookme.chatkit.extensions

import com.levit.bookme.chatkit.models.enums.MessageType
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import java.util.*

internal fun MessageView.emptyMessageModel(): MessageModel {
    return object : MessageModel {
        override val id: Long = 0
        override val text: String = ""
        override val author: String = ""
        override val date: Date = Date()
        override val authorImageUrlLink: String? = null
        override val type: MessageType = MessageType.YOUR_MESSAGE
    }
}