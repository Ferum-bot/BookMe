package com.levit.bookme.chatkit.extensions

import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.models.general_chat.GeneralChatModel
import com.levit.bookme.chatkit.ui.general_chat.GeneralChatView

internal fun GeneralChatView.provideEmptyModel(): GeneralChatModel {
    return object: GeneralChatModel {
        override val chats: List<ChatModel> = emptyList()
    }
}