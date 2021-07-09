package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.interfaces.MessageInputModel
import com.levit.bookme.chatkit.models.message_input.MessageInputOptions
import com.levit.bookme.chatkit.ui.message_input.MessageInputView

internal class DefaultMessageInputViewFactory(
    private val styleOptions: MessageInputOptions
) {

    fun createMessageInput(
        model: MessageInputModel, requireContext: () -> Context
    ): MessageInputView {
        return MessageInputView(requireContext.invoke())
    }

}