package com.levit.bookme.chatkit.ui.chat

import com.levit.bookme.chatkit.models.chat.ChatModel

open class SimpleChatListener: ChatListener {

    override fun onInterlocutorNameClicked(chatModel: ChatModel) = Unit
    override fun onLastMessageClicked(chatModel: ChatModel) = Unit
    override fun onProfileIconClicked(chatModel: ChatModel) = Unit
    override fun onProfileIconLongClicked(chatModel: ChatModel): Boolean = false
}