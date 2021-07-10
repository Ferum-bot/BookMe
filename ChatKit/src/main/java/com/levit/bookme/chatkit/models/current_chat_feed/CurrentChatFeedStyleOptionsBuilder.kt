package com.levit.bookme.chatkit.models.current_chat_feed

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension

class CurrentChatFeedStyleOptionsBuilder {

    companion object {

        private const val DEFAULT_EXTRA_SPACE_DP = 0
    }

    @ColorInt
    var messagesLayoutBackgroundColor: Int = Color.WHITE

    @Dimension(unit = Dimension.DP)
    var messagesLayoutExtraSpaceTopDp: Int = DEFAULT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var messagesLayoutExtraSpaceStartDp: Int = DEFAULT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var messagesLayoutExtraSpaceEndDp: Int = DEFAULT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var messagesLayoutExtraSpaceBottomDp: Int = DEFAULT_EXTRA_SPACE_DP
}