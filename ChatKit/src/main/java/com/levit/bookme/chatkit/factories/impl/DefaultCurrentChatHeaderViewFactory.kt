package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderVew

internal class DefaultCurrentChatHeaderViewFactory(
    private val styleOptions: CurrentChatHeaderStyleOptions,
) {

    fun createCurrentChatHeader(
        model: CurrentChatHeaderModel, requireContext: () -> Context
    ): CurrentChatHeaderVew {
        return CurrentChatHeaderVew(requireContext.invoke()).apply {
            styleOptions = this@DefaultCurrentChatHeaderViewFactory.styleOptions
            chatHeaderModel = model
        }
    }

    fun bindCurrentChatHeader(
        view: CurrentChatHeaderVew, model: CurrentChatHeaderModel
    ) {
        view.styleOptions = styleOptions
        view.chatHeaderModel = model
    }
}