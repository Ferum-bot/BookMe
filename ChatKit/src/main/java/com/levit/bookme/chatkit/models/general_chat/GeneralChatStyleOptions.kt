package com.levit.bookme.chatkit.models.general_chat

class GeneralChatStyleOptions(
    build: GeneralChatStyleOptionsBuilder.() -> GeneralChatStyleOptionsBuilder
) {

    companion object {

        fun provideDefaultStyleOptions(): GeneralChatStyleOptions {
            return GeneralChatStyleOptions {
                return@GeneralChatStyleOptions this
            }
        }
    }

}