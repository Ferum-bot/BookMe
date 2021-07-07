package com.levit.bookme.chatkit.factories.chat_messages

import android.content.Context
import com.levit.bookme.chatkit.models.MessageStyleOptions
import com.levit.bookme.chatkit.models.interfaces.MessageModel
import com.levit.bookme.chatkit.ui.chat_message.MessageView

internal abstract class ChatMessagesFactory(
    protected val yourMessagesOptions: MessageStyleOptions,
    protected val interlocutorMessageOptions: MessageStyleOptions,
) {

    abstract fun createMessageFrom(
        position: Int, allMessages: List<MessageModel>, requireContext: () -> Context,
    ): MessageView

}