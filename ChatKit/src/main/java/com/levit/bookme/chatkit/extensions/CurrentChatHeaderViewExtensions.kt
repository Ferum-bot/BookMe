package com.levit.bookme.chatkit.extensions

import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderVew

internal fun CurrentChatHeaderVew.provideEmptyModel(): CurrentChatHeaderModel {
    return object: CurrentChatHeaderModel {
        override val interlocutorName: String = ""
        override val additionalText: String? = null
        override val profileIconImageUrl: String = ""
    }
}