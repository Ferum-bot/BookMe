package com.levit.bookme.chatkit.models.general_chat

import com.levit.bookme.chatkit.models.chat.ChatModel

interface GeneralChatModel {

    /**
     * The list of chats to show in recycler view.
     */
    val chats: List<ChatModel>

}