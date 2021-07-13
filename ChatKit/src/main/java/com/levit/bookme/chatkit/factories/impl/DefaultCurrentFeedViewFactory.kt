package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedStyleOptions
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedModel
import com.levit.bookme.chatkit.models.current_chat_header.CurrentChatHeaderStyleOptions
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions
import com.levit.bookme.chatkit.ui.current_chat_feed.CurrentChatFeedView

internal class DefaultCurrentFeedViewFactory {

    fun createCurrentChatFeed(
        model: CurrentChatFeedModel?, requireContext: () -> Context,
        currentChatFeedStyleOptions: CurrentChatFeedStyleOptions,
    ): CurrentChatFeedView {
        if (model == null) {
            return CurrentChatFeedView(requireContext.invoke()).apply {
                this.currentChatFeedStyleOptions = currentChatFeedStyleOptions
            }
        }

        return CurrentChatFeedView(requireContext.invoke()).apply {
            this.currentChatFeedStyleOptions = currentChatFeedStyleOptions
            currentChatFeedModel = model
        }
    }

    fun bindCurrentChatFeed(
        view: CurrentChatFeedView, model: CurrentChatFeedModel,
        currentChatFeedStyleOptions: CurrentChatFeedStyleOptions,
    ) {
        view.currentChatFeedStyleOptions = currentChatFeedStyleOptions
        view.currentChatFeedModel = model
    }
}