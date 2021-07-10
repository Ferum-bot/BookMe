package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.factories.ChatKitViewFactory
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

internal class DefaultChatKitViewFactoryFacade(

    youChatMessageStyleOptions: MessageStyleOptions,
    interlocutorChatMessageStyleOptions: MessageStyleOptions,

    chatStyleOptions: ChatStyleOptions,

    currentChatFeedStyleOptions: CurrentChatFeedStyleOptions,
    currentChatHeaderStyleOptions: CurrentChatHeaderStyleOptions,

    generalChatStyleOptions: GeneralChatStyleOptions,

    messageInputOptions: MessageInputStyleOptions,
):
ChatKitViewFactory(
    youChatMessageStyleOptions, interlocutorChatMessageStyleOptions,
    chatStyleOptions, currentChatFeedStyleOptions,
    currentChatHeaderStyleOptions, generalChatStyleOptions,
    messageInputOptions
) {

    private val defaultChatMessagesFactoryDelegate by lazy {
        DefaultChatMessagesFactory(
            yourMessageOptions = youChatMessageStyleOptions,
            interlocutorMessageOptions = interlocutorChatMessageStyleOptions,
        )
    }

    private val defaultChatViewFactoryDelegate by lazy {
        DefaultChatViewFactory(chatStyleOptions)
    }

    private val defaultCurrentChatFeedViewFactoryDelegate by lazy {
        DefaultCurrentFeedViewFactory(currentChatFeedStyleOptions)
    }

    private val defaultCurrentChatHeaderViewFactoryDelegate by lazy {
        DefaultCurrentChatHeaderViewFactory(currentChatHeaderStyleOptions)
    }

    private val defaultGeneralChatViewFactoryDelegate by lazy {
        DefaultGeneralChatViewFactory(generalChatStyleOptions)
    }

    private val defaultMessageInputViewFactoryDelegate by lazy {
        DefaultMessageInputViewFactory(messageInputOptions)
    }

    override fun createMessageFrom(
        position: Int, allMessages: List<MessageModel>, requireContext: () -> Context
    ): MessageView {
        return defaultChatMessagesFactoryDelegate.createMessageFrom(
            position, allMessages, requireContext
        )
    }

    override fun createChatFrom(
        position: Int, allChats: List<ChatModel>, requireContext: () -> Context
    ): ChatView {
        return defaultChatViewFactoryDelegate.createChatFrom(
            position, allChats, requireContext
        )
    }

    override fun createCurrentChatHeader(
        model: CurrentChatHeaderModel, requireContext: () -> Context
    ): CurrentChatHeaderVew {
        return defaultCurrentChatHeaderViewFactoryDelegate.createCurrentChatHeader(
            model, requireContext
        )
    }

    override fun createCurrentChatFeed(
        model: CurrentChatFeedModel, requireContext: () -> Context
    ): CurrentChatFeedView {
        return defaultCurrentChatFeedViewFactoryDelegate.createCurrentChatFeed(
            model, requireContext
        )
    }

    override fun createGeneralChat(
        model: GeneralChatModel, requireContext: () -> Context
    ): GeneralChatView {
        return defaultGeneralChatViewFactoryDelegate.createGeneralChat(
            model, requireContext
        )
    }

    override fun createMessageInput(
        model: MessageInputModel, requireContext: () -> Context
    ): MessageInputView {
        return defaultMessageInputViewFactoryDelegate.createMessageInput(
            model, requireContext
        )
    }

}