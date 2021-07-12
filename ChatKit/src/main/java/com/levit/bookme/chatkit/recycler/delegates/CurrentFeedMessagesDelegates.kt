package com.levit.bookme.chatkit.recycler.delegates

import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.ui.chat_message.MessageListener

internal object CurrentFeedMessagesDelegates {

    var messageListener: MessageListener? = null

    var allMessages = listOf<MessageModel>()

}