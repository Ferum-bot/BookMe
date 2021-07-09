package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.models.interfaces.ChatModel
import com.levit.bookme.chatkit.ui.chat.ChatView

internal class DefaultChatViewFactory(
    private val styleOptions: ChatStyleOptions
) {

    fun createChatFrom(
        position: Int, allChats: List<ChatModel>, requireContext: () -> Context
    ): ChatView {
        return ChatView(requireContext.invoke())
    }

}