package com.levit.bookme.chatkit.ui.current_chat_feed

import com.levit.bookme.chatkit.models.enums.ScrollStates

class SimpleCurrentChatFeedListener: CurrentChatFeedListener {

    override fun onScrollStateChanged(newState: ScrollStates) = Unit
    override fun onScrolled(xOffset: Int, yOffset: Int) = Unit

}