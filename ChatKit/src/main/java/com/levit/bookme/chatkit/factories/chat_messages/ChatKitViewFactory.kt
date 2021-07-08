package com.levit.bookme.chatkit.factories.chat_messages

import android.content.Context
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

/**
 * This factory uses to create different Chat kit views.
 * Needed to have convenient access to different style options
 * and all views.
 */
internal abstract class ChatKitViewFactory(

    protected val youChatMessageStyleOptions: MessageStyleOptions,
    protected val interlocutorChatMessageStyleOptions: MessageStyleOptions,

    protected val chatStyleOptions: ChatStyleOptions,

    protected val currentChatFeedOptions: CurrentChatFeedOptions,

    protected val generalChatOptions: GeneralChatOptions,

    protected val messageInputOptions: MessageInputOptions,

) {

    abstract fun createMessageFrom(
        position: Int, allMessages: List<MessageModel>, requireContext: () -> Context
    ): MessageView

    abstract fun createChatFrom(
        position: Int, allChats: List<ChatModel>, requireContext: () -> Context
    ): ChatView

    abstract fun createCurrentChatFeed(
        model: CurrentChatFeedModel, requireContext: () -> Context
    ): CurrentChatFeedView

    abstract fun createGeneralChat(
        model: GeneralChatModel, requireContext: () -> Context
    ): GeneralChatView

    abstract fun createMessageInput(
        model: MessageInputModel, requireContext: () -> Context
    ): MessageInputView

}