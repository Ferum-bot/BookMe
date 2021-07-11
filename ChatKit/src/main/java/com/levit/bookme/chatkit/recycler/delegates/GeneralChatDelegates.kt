package com.levit.bookme.chatkit.recycler.delegates

import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.levit.book_me.chat_kit.R
import com.levit.bookme.chatkit.factories.ChatKitViewFactory
import com.levit.bookme.chatkit.models.chat.ChatModel

internal object GeneralChatDelegates {

    fun chatsDelegate(
        factory: ChatKitViewFactory
    ) = adapterDelegate<ChatModel, ChatModel>(
        R.layout.chat_layout,
        layoutInflater =
        { viewGroup: ViewGroup, i: Int ->
            val context = viewGroup.context
            return@adapterDelegate factory.createChatFrom(
                position = null, allChats = null, requireContext = { context }
            )
        }) {

        bind {

        }

    }

}