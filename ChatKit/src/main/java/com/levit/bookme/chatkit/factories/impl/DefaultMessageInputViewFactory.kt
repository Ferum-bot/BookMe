package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.message_input.MessageInputModel
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions
import com.levit.bookme.chatkit.ui.message_input.MessageInputView

internal class DefaultMessageInputViewFactory {

    fun createMessageInput(
        model: MessageInputModel?, requireContext: () -> Context,
        styleOptions: MessageInputStyleOptions
    ): MessageInputView {
        if (model == null) {
            return MessageInputView(requireContext.invoke()).apply {
                this.styleOptions = styleOptions
            }
        }

        return MessageInputView(requireContext.invoke()).apply {
            this.styleOptions = styleOptions
            messageInputModel = model
        }
    }

    fun bindMessageInput(
        view: MessageInputView, model: MessageInputModel,
        styleOptions: MessageInputStyleOptions
    ) {
        view.styleOptions = styleOptions
        view.messageInputModel = model
    }
}