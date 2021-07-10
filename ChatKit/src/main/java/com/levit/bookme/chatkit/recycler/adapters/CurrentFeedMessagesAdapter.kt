package com.levit.bookme.chatkit.recycler.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.levit.bookme.chatkit.models.chat_messages.MessageModel

internal class CurrentFeedMessagesAdapter: AsyncListDifferDelegationAdapter<MessageModel>(CALL_BACK) {

    init {
        delegatesManager
    }

    companion object {

        private val CALL_BACK = object: DiffUtil.ItemCallback<MessageModel>() {

            override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
                return oldItem.id == newItem.id && oldItem.author == newItem.author
                        && oldItem.authorImageUrlLink == newItem.authorImageUrlLink
                        && oldItem.date == newItem.date && oldItem.text == newItem.text
                        && oldItem.type == newItem.type
            }
        }
    }
}