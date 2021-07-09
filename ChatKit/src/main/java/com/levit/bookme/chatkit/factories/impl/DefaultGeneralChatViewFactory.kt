package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.general_chat.GeneralChatOptions
import com.levit.bookme.chatkit.models.interfaces.GeneralChatModel
import com.levit.bookme.chatkit.ui.general_chat.GeneralChatView

internal class DefaultGeneralChatViewFactory(
    private val styleOptions: GeneralChatOptions
) {

    fun createGeneralChat(
        model: GeneralChatModel, requireContext: () -> Context
    ): GeneralChatView {
        return GeneralChatView(requireContext.invoke())
    }

}