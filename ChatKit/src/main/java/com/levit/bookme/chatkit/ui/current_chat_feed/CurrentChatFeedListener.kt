package com.levit.bookme.chatkit.ui.current_chat_feed

import com.levit.bookme.chatkit.models.enums.ScrollStates

/**
 * This interface indicates the messages recycler view
 * scroll listener.
 */
interface CurrentChatFeedListener {

    fun onScrollStateChanged(newState: ScrollStates)

    fun onScrolled(xOffset: Int, yOffset: Int)

}