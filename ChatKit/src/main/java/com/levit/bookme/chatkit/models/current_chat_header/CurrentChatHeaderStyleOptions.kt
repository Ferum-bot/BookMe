package com.levit.bookme.chatkit.models.current_chat_header

class CurrentChatHeaderStyleOptions(
    build: CurrentChatHeaderStyleOptionsBuilder.() -> CurrentChatHeaderStyleOptionsBuilder
) {

    companion object {

        fun provideDefaultOptions(): CurrentChatHeaderStyleOptions {
            return CurrentChatHeaderStyleOptions {
                return@CurrentChatHeaderStyleOptions this
            }
        }
    }

}