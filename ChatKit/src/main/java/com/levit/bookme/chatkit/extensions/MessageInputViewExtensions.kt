package com.levit.bookme.chatkit.extensions

import com.levit.bookme.chatkit.models.message_input.MessageInputModel

internal fun getEmptyMessageInputModel(): MessageInputModel {
    return object: MessageInputModel {
        override val hintString: String? = null
        override val initialMessage: String? = null
    }
}