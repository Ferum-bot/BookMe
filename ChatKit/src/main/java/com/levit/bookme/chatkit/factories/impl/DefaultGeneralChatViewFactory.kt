package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.models.general_chat.GeneralChatStyleOptions
import com.levit.bookme.chatkit.models.general_chat.GeneralChatModel
import com.levit.bookme.chatkit.ui.general_chat.GeneralChatView

internal class DefaultGeneralChatViewFactory {

    fun createGeneralChat(
        model: GeneralChatModel?, requireContext: () -> Context,
        generalStyleOptions: GeneralChatStyleOptions,
        chatStyleOptions: ChatStyleOptions,
    ): GeneralChatView {
        if (model == null) {
            return GeneralChatView(requireContext.invoke()).apply {
                this.chatStyleOptions = chatStyleOptions
                this.generalStyleOptions = generalStyleOptions
            }
        }

        return GeneralChatView(requireContext.invoke()).apply {
            this.chatStyleOptions = chatStyleOptions
            this.generalStyleOptions = generalStyleOptions
            generalChatModel = model
        }
    }

    fun bindGeneralChat(
        view: GeneralChatView, model: GeneralChatModel,
        generalStyleOptions: GeneralChatStyleOptions,
        chatStyleOptions: ChatStyleOptions,
    ) {
        view.chatStyleOptions = chatStyleOptions
        view.generalStyleOptions = generalStyleOptions
        view.generalChatModel = model
    }
}