package com.levit.bookme.chatkit.ui.chat_message

import com.levit.bookme.chatkit.models.chat_messages.MessageModel

open class SimpleMessageListener: MessageListener{

    override fun onAuthorNameClicked(model: MessageModel) = Unit
    override fun onMessageClicked(model: MessageModel) = Unit
    override fun onMessageLongClicked(model: MessageModel) = Unit
    override fun onMessageStatusClicked(model: MessageModel) = Unit
    override fun onProfileIconClicked(model: MessageModel) = Unit
    override fun onProfileIconLongClicked(model: MessageModel) = Unit
}