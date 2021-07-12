package com.levit.bookme.chatkit.recycler.delegates

import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.levit.book_me.chat_kit.R
import com.levit.bookme.chatkit.factories.ChatKitViewFactory
import com.levit.bookme.chatkit.models.chat_messages.InterlocutorMessageModel
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.chat_messages.YourMessageModel
import com.levit.bookme.chatkit.ui.chat_message.InterlocutorMessageView
import com.levit.bookme.chatkit.ui.chat_message.MessageListener
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import com.levit.bookme.chatkit.ui.chat_message.YourMessageView

internal object CurrentFeedMessagesDelegates {

    var messageListener: MessageListener? = null

    var allMessages = listOf<MessageModel>()

    fun yourMessageDelegate(
        factory: ChatKitViewFactory,
        styleOptions: MessageStyleOptions,
    ) = adapterDelegate<YourMessageModel, MessageModel>(

        /**
         * Creating YourMessageView.
         */
        R.layout.your_chat_message_layout,
        layoutInflater =
        { viewGroup: ViewGroup, i: Int ->
            val context = viewGroup.context
            return@adapterDelegate factory.createYourMessageFrom(
                position = null, allMessages = null,
                styleOptions = styleOptions, requireContext = { context }
            )
        }

    ) {

        val messageView = itemView as? YourMessageView

        messageView?.listener = messageListener

        bind {
            messageView ?: return@bind

            factory.bindMessageFrom(
                view = messageView, position = adapterPosition,
                allMessages = allMessages, styleOptions, styleOptions
            )
        }
    }

    fun interlocutorMessageDelegate(
        factory: ChatKitViewFactory,
        styleOptions: MessageStyleOptions,
    ) = adapterDelegate<InterlocutorMessageModel, MessageModel>(

        /**
         * Creating InterlocutorMessageView.
         */
        R.layout.interlocutor_chat_message_layout,
        layoutInflater =
        { viewGroup: ViewGroup, i: Int ->
            val context = viewGroup.context
            return@adapterDelegate factory.createInterlocutorMessageFrom(
                position = null, allMessages = null,
                styleOptions = styleOptions, requireContext = { context }
            )
        }

    ) {

        val messageView = itemView as? InterlocutorMessageView

        messageView?.listener = messageListener

        bind {
            messageView ?: return@bind

            factory.bindMessageFrom(
                view = messageView, position = adapterPosition,
                allMessages = allMessages, styleOptions, styleOptions
            )
        }
    }
}