package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.models.general_chat.GeneralChatStyleOptions
import com.levit.bookme.chatkit.models.general_chat.GeneralChatModel
import com.levit.bookme.chatkit.ui.general_chat.GeneralChatView

internal class DefaultGeneralChatViewFactory(
    private val styleOptions: GeneralChatStyleOptions,
    private val chatStyleOptions: ChatStyleOptions,
) {

    fun createGeneralChat(
        model: GeneralChatModel, requireContext: () -> Context
    ): GeneralChatView {
        return GeneralChatView(requireContext.invoke()).apply {
            chatStyleOptions = this@DefaultGeneralChatViewFactory.chatStyleOptions
            generalStyleOptions = this@DefaultGeneralChatViewFactory.styleOptions
            generalChatModel = model
        }
    }

    fun bindGeneralChat(
        view: GeneralChatView, model: GeneralChatModel,
    ) {
        view.chatStyleOptions = chatStyleOptions
        view.generalStyleOptions = styleOptions
        view.generalChatModel = model
    }
}