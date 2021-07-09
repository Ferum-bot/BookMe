package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import com.levit.bookme.chatkit.models.current_chat_feed.CurrentChatFeedStyleOptions
import com.levit.bookme.chatkit.models.interfaces.CurrentChatFeedModel
import com.levit.bookme.chatkit.ui.current_chat_feed.CurrentChatFeedView

internal class DefaultCurrentFeedViewFactory(
    private val styleOptions: CurrentChatFeedStyleOptions,
) {

    fun createCurrentChatFeed(
        model: CurrentChatFeedModel, requireContext: () -> Context
    ): CurrentChatFeedView {
        return CurrentChatFeedView(requireContext.invoke())
    }

}