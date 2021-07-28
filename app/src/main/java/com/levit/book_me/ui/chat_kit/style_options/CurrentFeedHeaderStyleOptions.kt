package com.levit.book_me.ui.chat_kit

import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions

fun provideDefaultCurrentHeadStyleOptions() = CurrentChatHeaderStyleOptions {
    interlocutorNameTextSizeSp = 17
    additionalTextSizeSp = 13
    return@CurrentChatHeaderStyleOptions this
}