package com.levit.bookme.chatkit.ui.chat

import com.levit.bookme.chatkit.models.chat.ChatModel

interface ChatListener {

    fun onProfileIconClicked(chatModel: ChatModel)

    fun onProfileIconLongClicked(chatModel: ChatModel): Boolean

    fun onInterlocutorNameClicked(chatModel: ChatModel)

    fun onLastMessageClicked(chatModel: ChatModel)
}