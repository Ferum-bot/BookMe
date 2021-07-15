package com.levit.bookme.chatkit.extensions

import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedModel
import com.levit.bookme.chatkit.ui.current_chat_feed.CurrentChatFeedView

internal fun CurrentChatFeedView.provideEmptyModel(): CurrentChatFeedModel {
    return object: CurrentChatFeedModel {
        override val allMessages: List<MessageModel> = emptyList()
    }
}