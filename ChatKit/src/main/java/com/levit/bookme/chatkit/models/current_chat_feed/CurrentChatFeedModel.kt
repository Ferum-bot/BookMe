package com.levit.bookme.chatkit.models.current_chat_feed

import com.levit.bookme.chatkit.models.chat_messages.MessageModel

interface CurrentChatFeedModel {

    val allMessages: List<MessageModel>

}