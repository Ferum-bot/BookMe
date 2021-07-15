package com.levit.bookme.chatkit.ui.general_chat

import androidx.recyclerview.widget.RecyclerView
import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.models.enums.ScrollStates

/**
 * This interface indicates the chats recycler view
 * onScrollListener
 */
interface GeneralChatListener {

    fun onScrollStateChanged(newState: ScrollStates)

    fun onScrolled(xOffset: Int, yOffset: Int)

}