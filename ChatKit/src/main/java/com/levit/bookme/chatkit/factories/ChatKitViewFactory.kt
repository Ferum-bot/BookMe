package com.levit.bookme.chatkit.factories

import android.content.Context
import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedModel
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedStyleOptions
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.models.general_chat.GeneralChatModel
import com.levit.bookme.chatkit.models.general_chat.GeneralChatStyleOptions
import com.levit.bookme.chatkit.models.message_input.MessageInputModel
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions
import com.levit.bookme.chatkit.ui.chat.ChatView
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import com.levit.bookme.chatkit.ui.current_chat_feed.CurrentChatFeedView
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderVew
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

    protected val currentChatFeedStyleOptions: CurrentChatFeedStyleOptions,
    protected val currentChatHeaderStyleOptions: CurrentChatHeaderStyleOptions,

    protected val generalChatStyleOptions: GeneralChatStyleOptions,

    protected val messageInputOptions: MessageInputStyleOptions,

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

    abstract fun createCurrentChatHeader(
        model: CurrentChatHeaderModel, requireContext: () -> Context
    ): CurrentChatHeaderVew

    abstract fun createGeneralChat(
        model: GeneralChatModel, requireContext: () -> Context
    ): GeneralChatView

    abstract fun createMessageInput(
        model: MessageInputModel, requireContext: () -> Context
    ): MessageInputView

}