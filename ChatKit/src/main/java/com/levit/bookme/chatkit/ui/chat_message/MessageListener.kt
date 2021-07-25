package com.levit.bookme.chatkit.ui.chat_message

import com.levit.bookme.chatkit.models.chat_messages.MessageModel

interface MessageListener {

    fun onMessageClicked(model: MessageModel)

    fun onMessageLongClicked(model: MessageModel)

    fun onProfileIconClicked(model: MessageModel)

    fun onProfileIconLongClicked(model: MessageModel)

    fun onAuthorNameClicked(model: MessageModel)

    fun onMessageStatusClicked(model: MessageModel)
}