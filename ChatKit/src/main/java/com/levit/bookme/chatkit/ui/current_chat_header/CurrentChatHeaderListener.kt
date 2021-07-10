package com.levit.bookme.chatkit.ui.current_chat_header

import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel

interface CurrentChatHeaderListener {

    fun onBackButtonClicked(model: CurrentChatHeaderModel)

    fun onProfileIconClicked(model: CurrentChatHeaderModel)

    fun onInterlocutorNameClicked(model: CurrentChatHeaderModel)

    fun onAdditionalTextClicked(model: CurrentChatHeaderModel)

}