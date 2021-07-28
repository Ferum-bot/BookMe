package com.levit.bookme.chatkit.ui.current_chat_header

import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel

open class SimpleCurrentChatHeaderListener: CurrentChatHeaderListener {

    override fun onAdditionalTextClicked(model: CurrentChatHeaderModel) = Unit
    override fun onBackButtonClicked(model: CurrentChatHeaderModel) = Unit
    override fun onInterlocutorNameClicked(model: CurrentChatHeaderModel) = Unit
    override fun onProfileIconClicked(model: CurrentChatHeaderModel) = Unit

}