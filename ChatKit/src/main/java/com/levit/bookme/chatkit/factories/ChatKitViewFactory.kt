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
import com.levit.bookme.chatkit.ui.chat_message.InterlocutorMessageView
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import com.levit.bookme.chatkit.ui.chat_message.YourMessageView
import com.levit.bookme.chatkit.ui.current_chat_feed.CurrentChatFeedView
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderVew
import com.levit.bookme.chatkit.ui.general_chat.GeneralChatView
import com.levit.bookme.chatkit.ui.message_input.MessageInputView

/**
 * This factory uses to create different Chat kit views.
 * Needed to have convenient access to different style options
 * and all views.
 */
internal abstract class ChatKitViewFactory {

    /**
     * If properties is null, must return empty message view.
     */
    abstract fun createYourMessageFrom(
        position: Int?, allMessages: List<MessageModel>?,
        styleOptions: MessageStyleOptions, requireContext: () -> Context
    ): YourMessageView

    /**
     * If properties is null, must return empty message view.
     */
    abstract fun createInterlocutorMessageFrom(
        position: Int?, allMessages: List<MessageModel>?,
        styleOptions: MessageStyleOptions ,requireContext: () -> Context
    ): InterlocutorMessageView

    abstract fun bindMessageFrom(
        view: MessageView, position: Int, allMessages: List<MessageModel>,
        yourStyleOptions: MessageStyleOptions,
        interlocutorStyleOptions: MessageStyleOptions,
    )

    /**
     * If properties is null, must return empty chat view.
     */
    abstract fun createChatFrom(
        position: Int?, allChats: List<ChatModel>?,
        styleOptions: ChatStyleOptions, requireContext: () -> Context
    ): ChatView

    abstract fun bindChatFrom(
        view: ChatView, position: Int, allChats: List<ChatModel>,
        styleOptions: ChatStyleOptions,
    )

    /**
     * If model is null, must return empty current chat feed view.
     */
    abstract fun createCurrentChatFeed(
        model: CurrentChatFeedModel?, requireContext: () -> Context,
        styleOptions: CurrentChatFeedStyleOptions,
    ): CurrentChatFeedView

    abstract fun bindCurrentChatFeed(
        view: CurrentChatFeedView, model: CurrentChatFeedModel,
        styleOptions: CurrentChatFeedStyleOptions,
    )

    /**
     * If model is null, must return empty current chat header view.
     */
    abstract fun createCurrentChatHeader(
        model: CurrentChatHeaderModel?, requireContext: () -> Context,
        styleOptions: CurrentChatHeaderStyleOptions,
    ): CurrentChatHeaderVew

    abstract fun bindCurrentChatHeader(
        view: CurrentChatHeaderVew, model: CurrentChatHeaderModel,
        styleOptions: CurrentChatHeaderStyleOptions,
    )

    /**
     * if model is null, must return empty general chat view.
     */
    abstract fun createGeneralChat(
        model: GeneralChatModel?, requireContext: () -> Context,
        generalStyleOptions: GeneralChatStyleOptions,
        chatStyleOptions: ChatStyleOptions,
    ): GeneralChatView

    abstract fun bindGeneralChat(
        view: GeneralChatView, model: GeneralChatModel,
        generalStyleOptions: GeneralChatStyleOptions,
        chatStyleOptions: ChatStyleOptions,
    )

    /**
     * If model is null, must return empty message input view.
     */
    abstract fun createMessageInput(
        model: MessageInputModel?, requireContext: () -> Context,
        styleOptions: MessageInputStyleOptions,
    ): MessageInputView

    abstract fun bindMessageInput(
        view: MessageInputView, model: MessageInputModel,
        styleOptions: MessageInputStyleOptions,
    )

}