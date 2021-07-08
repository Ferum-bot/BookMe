package com.levit.bookme.chatkit.factories.chat_messages.impl

import android.content.Context
import com.levit.bookme.chatkit.factories.chat_messages.ChatKitViewFactory
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedOptions
import com.levit.bookme.chatkit.models.general_chat.GeneralChatOptions
import com.levit.bookme.chatkit.models.interfaces.*
import com.levit.bookme.chatkit.models.message_input.MessageInputOptions
import com.levit.bookme.chatkit.ui.chat.ChatView
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import com.levit.bookme.chatkit.ui.current_chat_feed.CurrentChatFeedView
import com.levit.bookme.chatkit.ui.general_chat.GeneralChatView
import com.levit.bookme.chatkit.ui.message_input.MessageInputView

internal class DefaultChatKitViewFactoryFacade(

    youChatMessageStyleOptions: MessageStyleOptions,
    interlocutorChatMessageStyleOptions: MessageStyleOptions,

    chatStyleOptions: ChatStyleOptions,

    currentChatFeedOptions: CurrentChatFeedOptions,

    generalChatOptions: GeneralChatOptions,

    messageInputOptions: MessageInputOptions,


): ChatKitViewFactory(
    youChatMessageStyleOptions, interlocutorChatMessageStyleOptions,
    chatStyleOptions, currentChatFeedOptions,
    generalChatOptions, messageInputOptions
) {

    override fun createMessageFrom(
        position: Int, allMessages: List<MessageModel>, requireContext: () -> Context
    ): MessageView {
        TODO("Not yet implemented")
    }

    override fun createChatFrom(
        position: Int, allChats: List<ChatModel>, requireContext: () -> Context
    ): ChatView {
        TODO("Not yet implemented")
    }

    override fun createCurrentChatFeed(
        model: CurrentChatFeedModel, requireContext: () -> Context
    ): CurrentChatFeedView {
        TODO("Not yet implemented")
    }

    override fun createGeneralChat(
        model: GeneralChatModel, requireContext: () -> Context
    ): GeneralChatView {
        TODO("Not yet implemented")
    }

    override fun createMessageInput(
        model: MessageInputModel, requireContext: () -> Context
    ): MessageInputView {
        TODO("Not yet implemented")
    }

}