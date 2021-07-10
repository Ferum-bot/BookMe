package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.message_input.MessageInputModel
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions
import com.levit.bookme.chatkit.ui.message_input.MessageInputView

internal class DefaultMessageInputViewFactory(
    private val styleOptions: MessageInputStyleOptions
) {

    fun createMessageInput(
        model: MessageInputModel, requireContext: () -> Context
    ): MessageInputView {
        return MessageInputView(requireContext.invoke())
    }

}