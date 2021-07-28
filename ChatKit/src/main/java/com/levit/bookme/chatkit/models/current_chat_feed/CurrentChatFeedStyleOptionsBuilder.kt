package com.levit.bookme.chatkit.models.current_chat_feed

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.ChatFeedEmptyMessageFormat

class CurrentChatFeedStyleOptionsBuilder {

    companion object {

        private const val DEFAULT_EXTRA_SPACE_DP = 0

        private const val DEFAULT_TEXT_SIZE_SP = 18
    }

    @ColorInt
    var messagesLayoutBackgroundColor: Int = Color.WHITE
    @ColorInt
    var emptyChatFeedTextMessageColor: Int = Color.BLACK

    var emptyChatFeedFormat: ChatFeedEmptyMessageFormat = ChatFeedEmptyMessageFormat.SHOW_ONLY_TEXT

    @Dimension(unit = Dimension.SP)
    var emptyChatFeedTextMessageSizeSp: Int = DEFAULT_TEXT_SIZE_SP

    @Dimension(unit = Dimension.DP)
    var messagesLayoutExtraSpaceTopDp: Int = DEFAULT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var messagesLayoutExtraSpaceStartDp: Int = DEFAULT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var messagesLayoutExtraSpaceEndDp: Int = DEFAULT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var messagesLayoutExtraSpaceBottomDp: Int = DEFAULT_EXTRA_SPACE_DP
}