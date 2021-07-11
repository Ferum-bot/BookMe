package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.ui.current_chat_header.CurrentChatHeaderVew

internal class DefaultCurrentChatHeaderViewFactory {

    fun createCurrentChatHeader(
        model: CurrentChatHeaderModel, requireContext: () -> Context,
        styleOptions: CurrentChatHeaderStyleOptions,
    ): CurrentChatHeaderVew {
        return CurrentChatHeaderVew(requireContext.invoke()).apply {
            this.styleOptions = styleOptions
            chatHeaderModel = model
        }
    }

    fun bindCurrentChatHeader(
        view: CurrentChatHeaderVew, model: CurrentChatHeaderModel,
        styleOptions: CurrentChatHeaderStyleOptions
    ) {
        view.styleOptions = styleOptions
        view.chatHeaderModel = model
    }
}