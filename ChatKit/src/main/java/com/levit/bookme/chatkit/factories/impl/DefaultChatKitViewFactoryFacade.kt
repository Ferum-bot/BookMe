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
import com.levit.bookme.chatkit.ui.chat_message.InterlocutorMessageView
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import com.levit.bookme.chatkit.ui.chat_message.YourMessageView
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
        DefaultGeneralChatViewFactory(
            generalChatStyleOptions, chatStyleOptions
        )
    }

    private val defaultMessageInputViewFactoryDelegate by lazy {
        DefaultMessageInputViewFactory(messageInputOptions)
    }

    override fun createYourMessageFrom(
        position: Int?, allMessages: List<MessageModel>?, requireContext: () -> Context
    ): YourMessageView {
        if (position == null || allMessages == null) {
            return YourMessageView(requireContext.invoke()).apply {
                styleOptions = youChatMessageStyleOptions
            }
        }

        return defaultChatMessagesFactoryDelegate.createYourMessageFrom(
            position, allMessages, requireContext
        )
    }

    override fun createInterlocutorMessageFrom(
        position: Int?, allMessages: List<MessageModel>?, requireContext: () -> Context
    ): InterlocutorMessageView {
        if (position == null || allMessages == null) {
            return InterlocutorMessageView(requireContext.invoke()).apply {
                styleOptions = interlocutorChatMessageStyleOptions
            }
        }

        return defaultChatMessagesFactoryDelegate.createInterlocutorMessageFrom(
            position, allMessages, requireContext
        )
    }

    override fun bindMessageFrom(
        view: MessageView, position: Int, allMessages: List<MessageModel>
    ) {
        defaultChatMessagesFactoryDelegate.bindMessageFrom(
            view, position, allMessages
        )
    }

    override fun createChatFrom(
        position: Int?, allChats: List<ChatModel>?, requireContext: () -> Context
    ): ChatView {
        if (position == null || allChats == null) {
            return ChatView(requireContext.invoke()).apply {
                styleOptions = chatStyleOptions
            }
        }

        return defaultChatViewFactoryDelegate.createChatFrom(
            position, allChats, requireContext
        )
    }

    override fun bindChatFrom(
        view: ChatView, position: Int, allChats: List<ChatModel>
    ) {
        defaultChatViewFactoryDelegate.bindChatFrom(
            view, position, allChats
        )
    }

    override fun createCurrentChatHeader(
        model: CurrentChatHeaderModel?, requireContext: () -> Context
    ): CurrentChatHeaderVew {
        if (model == null) {
            return CurrentChatHeaderVew(requireContext.invoke()).apply {
                styleOptions = currentChatHeaderStyleOptions
            }
        }

        return defaultCurrentChatHeaderViewFactoryDelegate.createCurrentChatHeader(
            model, requireContext
        )
    }

    override fun bindCurrentChatHeader(
        view: CurrentChatHeaderVew, model: CurrentChatHeaderModel
    ) {
        defaultCurrentChatHeaderViewFactoryDelegate.bindCurrentChatHeader(
            view, model
        )
    }

    override fun createCurrentChatFeed(
        model: CurrentChatFeedModel?, requireContext: () -> Context
    ): CurrentChatFeedView {
        if (model == null) {
            return CurrentChatFeedView(requireContext.invoke()).apply {
                styleOptions = currentChatFeedStyleOptions
            }
        }

        return defaultCurrentChatFeedViewFactoryDelegate.createCurrentChatFeed(
            model, requireContext
        )
    }

    override fun bindCurrentChatFeed(
        view: CurrentChatFeedView, model: CurrentChatFeedModel
    ) {
        defaultCurrentChatFeedViewFactoryDelegate.bindCurrentChatFeed(
            view, model
        )
    }

    override fun createGeneralChat(
        model: GeneralChatModel?, requireContext: () -> Context
    ): GeneralChatView {
        if (model == null) {
            return GeneralChatView(requireContext.invoke()).apply {
                this.chatStyleOptions = this@DefaultChatKitViewFactoryFacade.chatStyleOptions
                this.generalStyleOptions = this@DefaultChatKitViewFactoryFacade.generalChatStyleOptions
            }
        }

        return defaultGeneralChatViewFactoryDelegate.createGeneralChat(
            model, requireContext
        )
    }

    override fun bindGeneralChat(
        view: GeneralChatView, model: GeneralChatModel
    ) {
        defaultGeneralChatViewFactoryDelegate.bindGeneralChat(
            view, model
        )
    }

    override fun createMessageInput(
        model: MessageInputModel?, requireContext: () -> Context
    ): MessageInputView {
        if (model == null) {
            return MessageInputView(requireContext.invoke()).apply {
                styleOptions = messageInputOptions
            }
        }

        return defaultMessageInputViewFactoryDelegate.createMessageInput(
            model, requireContext
        )
    }

    override fun bindMessageInput(
        view: MessageInputView, model: MessageInputModel
    ) {
        defaultMessageInputViewFactoryDelegate.bindMessageInput(
            view, model
        )
    }
}