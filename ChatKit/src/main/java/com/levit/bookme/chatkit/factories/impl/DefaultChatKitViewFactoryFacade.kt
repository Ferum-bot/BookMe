package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
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

internal class DefaultChatKitViewFactoryFacade():
ChatKitViewFactory() {

    private val defaultChatMessagesFactoryDelegate by lazy {
        DefaultChatMessagesFactory()
    }

    private val defaultChatViewFactoryDelegate by lazy {
        DefaultChatViewFactory()
    }

    private val defaultCurrentChatFeedViewFactoryDelegate by lazy {
        DefaultCurrentFeedViewFactory()
    }

    private val defaultCurrentChatHeaderViewFactoryDelegate by lazy {
        DefaultCurrentChatHeaderViewFactory()
    }

    private val defaultGeneralChatViewFactoryDelegate by lazy {
        DefaultGeneralChatViewFactory()
    }

    private val defaultMessageInputViewFactoryDelegate by lazy {
        DefaultMessageInputViewFactory()
    }

    override fun createYourMessageFrom(
        position: Int?, allMessages: List<MessageModel>?,
        styleOptions: MessageStyleOptions, requireContext: () -> Context,
    ): YourMessageView {
        return defaultChatMessagesFactoryDelegate.createYourMessageFrom(
            position, allMessages, requireContext, styleOptions
        )
    }

    override fun createInterlocutorMessageFrom(
        position: Int?, allMessages: List<MessageModel>?,
        styleOptions: MessageStyleOptions, requireContext: () -> Context
    ): InterlocutorMessageView {
        return defaultChatMessagesFactoryDelegate.createInterlocutorMessageFrom(
            position, allMessages, requireContext, styleOptions
        )
    }

    override fun bindMessageFrom(
        view: MessageView, position: Int, allMessages: List<MessageModel>,
        yourStyleOptions: MessageStyleOptions,
        interlocutorStyleOptions: MessageStyleOptions,
    ) {
        defaultChatMessagesFactoryDelegate.bindMessageFrom(
            view, position, allMessages, yourStyleOptions, interlocutorStyleOptions,
        )
    }

    override fun createChatFrom(
        position: Int?, allChats: List<ChatModel>?,
        styleOptions: ChatStyleOptions, requireContext: () -> Context
    ): ChatView {
        return defaultChatViewFactoryDelegate.createChatFrom(
            position, allChats, requireContext, styleOptions
        )
    }

    override fun bindChatFrom(
        view: ChatView, position: Int, allChats: List<ChatModel>,
        styleOptions: ChatStyleOptions,
    ) {
        defaultChatViewFactoryDelegate.bindChatFrom(
            view, position, allChats, styleOptions
        )
    }

    override fun createCurrentChatHeader(
        model: CurrentChatHeaderModel?, requireContext: () -> Context,
        styleOptions: CurrentChatHeaderStyleOptions,
    ): CurrentChatHeaderVew {
        return defaultCurrentChatHeaderViewFactoryDelegate.createCurrentChatHeader(
            model, requireContext, styleOptions
        )
    }

    override fun bindCurrentChatHeader(
        view: CurrentChatHeaderVew, model: CurrentChatHeaderModel,
        styleOptions: CurrentChatHeaderStyleOptions,
    ) {
        defaultCurrentChatHeaderViewFactoryDelegate.bindCurrentChatHeader(
            view, model, styleOptions
        )
    }

    override fun createCurrentChatFeed(
        model: CurrentChatFeedModel?, requireContext: () -> Context,
        styleOptions: CurrentChatFeedStyleOptions,
    ): CurrentChatFeedView {
        return defaultCurrentChatFeedViewFactoryDelegate.createCurrentChatFeed(
            model, requireContext, styleOptions
        )
    }

    override fun bindCurrentChatFeed(
        view: CurrentChatFeedView, model: CurrentChatFeedModel,
        styleOptions: CurrentChatFeedStyleOptions,
    ) {
        defaultCurrentChatFeedViewFactoryDelegate.bindCurrentChatFeed(
            view, model, styleOptions
        )
    }

    override fun createGeneralChat(
        model: GeneralChatModel?, requireContext: () -> Context,
        generalStyleOptions: GeneralChatStyleOptions,
        chatStyleOptions: ChatStyleOptions,
    ): GeneralChatView {
        return defaultGeneralChatViewFactoryDelegate.createGeneralChat(
            model, requireContext, generalStyleOptions, chatStyleOptions
        )
    }

    override fun bindGeneralChat(
        view: GeneralChatView, model: GeneralChatModel,
        generalStyleOptions: GeneralChatStyleOptions,
        chatStyleOptions: ChatStyleOptions,
    ) {
        defaultGeneralChatViewFactoryDelegate.bindGeneralChat(
            view, model, generalStyleOptions, chatStyleOptions
        )
    }

    override fun createMessageInput(
        model: MessageInputModel?, requireContext: () -> Context,
        styleOptions: MessageInputStyleOptions,
    ): MessageInputView {
        return defaultMessageInputViewFactoryDelegate.createMessageInput(
            model, requireContext, styleOptions
        )
    }

    override fun bindMessageInput(
        view: MessageInputView, model: MessageInputModel,
        styleOptions: MessageInputStyleOptions,
    ) {
        defaultMessageInputViewFactoryDelegate.bindMessageInput(
            view, model, styleOptions
        )
    }
}