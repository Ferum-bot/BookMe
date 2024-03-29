package com.levit.bookme.chatkit.recycler.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.levit.bookme.chatkit.factories.ChatKitViewFactory
import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.recycler.delegates.GeneralChatDelegates
import com.levit.bookme.chatkit.ui.chat.ChatListener

internal class GeneralChatAdapter(
    chatKitFactory: ChatKitViewFactory,
    chatOptions: ChatStyleOptions,
): AsyncListDifferDelegationAdapter<ChatModel>(CALL_BACK) {

    init {
        delegatesManager
            .addDelegate(GeneralChatDelegates.chatsDelegate(chatKitFactory, chatOptions))
    }

    companion object {

        private val CALL_BACK = object: DiffUtil.ItemCallback<ChatModel>() {

            override fun areItemsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ChatModel, newItem: ChatModel): Boolean {
                return oldItem.id == newItem.id && oldItem.dateOfLastMessage == newItem.dateOfLastMessage
                        && oldItem.interlocutorName == newItem.interlocutorName
                        && oldItem.interlocutorProfileImageUrl == newItem.interlocutorProfileImageUrl
                        && oldItem.lastMessage == newItem.lastMessage
                        && oldItem.lastMessageFrom == newItem.lastMessageFrom
                        && oldItem.numberOfUnreadMessaged == newItem.numberOfUnreadMessaged
            }
        }
    }
}