package com.levit.bookme.chatkit.models.current_chat_feed

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension

class CurrentChatFeedStyleOptions(
    build: CurrentChatFeedStyleOptionsBuilder.() -> CurrentChatFeedStyleOptionsBuilder
) {

    @ColorInt
    val messagesLayoutBackgroundColor: Int

    @Dimension(unit = Dimension.DP)
    val messagesLayoutExtraSpaceTopDp: Int
    @Dimension(unit = Dimension.DP)
    val messagesLayoutExtraSpaceStartDp: Int
    @Dimension(unit = Dimension.DP)
    val messagesLayoutExtraSpaceEndDp: Int
    @Dimension(unit = Dimension.DP)
    val messagesLayoutExtraSpaceBottomDp: Int

    init {
        val options = CurrentChatFeedStyleOptionsBuilder().build()

        this.messagesLayoutBackgroundColor = options.messagesLayoutBackgroundColor

        this.messagesLayoutExtraSpaceTopDp = options.messagesLayoutExtraSpaceTopDp
        this.messagesLayoutExtraSpaceStartDp = options.messagesLayoutExtraSpaceStartDp
        this.messagesLayoutExtraSpaceEndDp = options.messagesLayoutExtraSpaceEndDp
        this.messagesLayoutExtraSpaceBottomDp = options.messagesLayoutExtraSpaceBottomDp
    }
}