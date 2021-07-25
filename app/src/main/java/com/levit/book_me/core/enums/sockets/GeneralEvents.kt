package com.levit.book_me.core.enums.sockets

sealed class GeneralEvents {

    class ChatMessagesEvent(val chatId: Long): GeneralEvents()

}

sealed class CurrentChatEvents {

    class InterlocutorOnline(val isOnline: Boolean): CurrentChatEvents()

    class InterlocutorTyping(val isTyping: Boolean): CurrentChatEvents()

}