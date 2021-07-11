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
        if (position == null || allMessages == null) {
            return YourMessageView(requireContext.invoke()).apply {
                this.styleOptions = styleOptions
            }
        }

        return defaultChatMessagesFactoryDelegate.createYourMessageFrom(
            position, allMessages, requireContext, styleOptions
        )
    }

    override fun createInterlocutorMessageFrom(
        position: Int?, allMessages: List<MessageModel>?,
        styleOptions: MessageStyleOptions, requireContext: () -> Context
    ): InterlocutorMessageView {
        if (position == null || allMessages == null) {
            return InterlocutorMessageView(requireContext.invoke()).apply {
                this.styleOptions = styleOptions
            }
        }

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
        if (position == null || allChats == null) {
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
            )
            return ChatView(requireContext.invoke()).apply {
                this.styleOptions = styleOptions
                this.layoutParams = layoutParams
            }
        }

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
        if (model == null) {
            return CurrentChatHeaderVew(requireContext.invoke()).apply {
                this.styleOptions = styleOptions
            }
        }

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
        if (model == null) {
            return CurrentChatFeedView(requireContext.invoke()).apply {
                this.styleOptions = styleOptions
            }
        }

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
        if (model == null) {
            return GeneralChatView(requireContext.invoke()).apply {
                this.chatStyleOptions = chatStyleOptions
                this.generalStyleOptions = generalStyleOptions
            }
        }

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
        if (model == null) {
            return MessageInputView(requireContext.invoke()).apply {
                this.styleOptions = styleOptions
            }
        }

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