package com.levit.bookme.chatkit.models.chat

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment

class ChatStyleOptionsBuilder {

    companion object {

        private const val DEFAULT_BIG_TEXT_SIZE_SP = 18
        private const val DEFAULT_SMALL_TEXT_SIZE_SP = 12

        private const val DEFAULT_BIG_MARGIN_DP = 8
        private const val DEFAULT_SMALL_MARGIN_DP = 4
    }

    @ColorInt
    var interlocutorNameTextColor: Int = Color.BLACK
    @ColorInt
    var lastMessageTextColor: Int = Color.GRAY
    @ColorInt
    var lastMessageDateTextColor: Int = Color.LTGRAY

    @Dimension
    var interlocutorNameTextSizeSp: Int = DEFAULT_BIG_TEXT_SIZE_SP
    @Dimension
    var lastMessageTextSizeSp: Int = DEFAULT_SMALL_TEXT_SIZE_SP
    @Dimension
    var lastMessageDateTextSizeSp: Int = DEFAULT_SMALL_TEXT_SIZE_SP

    @Dimension
    var interlocutorNameMarginTopDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension
    var interlocutorNameMarginBottomDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension
    var interlocutorNameMarginStartDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension
    var interlocutorNameMarginEndDp: Int = DEFAULT_BIG_MARGIN_DP

    @Dimension
    var lastMessageMarginStartDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension
    var lastMessageMarginEndDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension
    var lastMessageMarginTopDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension
    var lastMessageMarginBottomDp: Int = DEFAULT_SMALL_MARGIN_DP

    @Dimension
    var lastMessageDateMarginStartDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension
    var lastMessageDateMarginEndDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension
    var lastMessageDateMarginTopDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension
    var lastMessageDateMarginBottomDp: Int = DEFAULT_SMALL_MARGIN_DP

    var interlocutorNameAlignment: MessageTextAlignment = MessageTextAlignment.START

    var showOnlineStatus: Boolean = true
    var showProfileIcon: Boolean = true
    var showLastMessageDate: Boolean = true
}
