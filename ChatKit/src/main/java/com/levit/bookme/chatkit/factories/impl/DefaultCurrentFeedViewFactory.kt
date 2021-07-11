package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedStyleOptions
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedModel
import com.levit.bookme.chatkit.ui.current_chat_feed.CurrentChatFeedView

internal class DefaultCurrentFeedViewFactory {

    fun createCurrentChatFeed(
        model: CurrentChatFeedModel?, requireContext: () -> Context,
        styleOptions: CurrentChatFeedStyleOptions,
    ): CurrentChatFeedView {
        if (model == null) {
            return CurrentChatFeedView(requireContext.invoke()).apply {
                this.styleOptions = styleOptions
            }
        }

        return CurrentChatFeedView(requireContext.invoke()).apply {
            this.styleOptions = styleOptions
            currentChatFeedModel = model
        }
    }

    fun bindCurrentChatFeed(
        view: CurrentChatFeedView, model: CurrentChatFeedModel,
        styleOptions: CurrentChatFeedStyleOptions,
    ) {
        view.styleOptions = styleOptions
        view.currentChatFeedModel = model
    }
}