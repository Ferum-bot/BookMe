package com.levit.bookme.chatkit.recycler.delegates

import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.levit.book_me.chat_kit.R
import com.levit.bookme.chatkit.factories.ChatKitViewFactory
import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.ui.chat.ChatListener
import com.levit.bookme.chatkit.ui.chat.ChatView

internal object GeneralChatDelegates {

    var chatListener: ChatListener? = null

    var allChats: List<ChatModel> = emptyList()

    fun chatsDelegate(
        factory: ChatKitViewFactory,
        chatOptions: ChatStyleOptions,
    ) = adapterDelegate<ChatModel, ChatModel>(

        /**
         * Creating ChatView.
         */
        R.layout.chat_layout,
        layoutInflater =
        { viewGroup: ViewGroup, i: Int ->
            val context = viewGroup.context
            return@adapterDelegate factory.createChatFrom(
                position = null, allChats = null,
                styleOptions = chatOptions, requireContext = { context }
            )
        }

    ) {

        val chatView = itemView as? ChatView

        chatView?.listener = chatListener

        bind { currentList ->
            chatView ?: return@bind

            factory.bindChatFrom(
                view = chatView, position = adapterPosition,
                allChats = allChats, styleOptions = chatOptions,
            )
        }

    }

}