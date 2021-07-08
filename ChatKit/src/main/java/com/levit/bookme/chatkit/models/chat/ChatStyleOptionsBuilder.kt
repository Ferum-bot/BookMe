package com.levit.bookme.chatkit.models.chat

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.MessageDateFormat
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment

class ChatStyleOptionsBuilder {

    companion object {

        private const val DEFAULT_BIG_TEXT_SIZE_SP = 18
        private const val DEFAULT_SMALL_TEXT_SIZE_SP = 12

        private const val DEFAULT_BIG_MARGIN_DP = 8
        private const val DEFAULT_SMALL_MARGIN_DP = 4

        private const val DEFAULT_BACKGROUND_RADIUS_DP = 0
        private const val DEFAULT_STROKE_WIDTH_DP = 0
    }

    @ColorInt
    var interlocutorNameTextColor: Int = Color.BLACK
    @ColorInt
    var lastMessageTextColor: Int = Color.GRAY
    @ColorInt
    var lastMessageDateTextColor: Int = Color.LTGRAY
    @ColorInt
    var backgroundColor: Int = Color.WHITE
    @ColorInt
    var backgroundStrokeColor: Int = Color.WHITE

    @Dimension
    var backgroundStrokeWidthDp: Int = DEFAULT_STROKE_WIDTH_DP

    @Dimension
    var backgroundTopLeftRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension
    var backgroundTopRightRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension
    var backgroundBottomLeftRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension
    var backgroundBottomRightRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP

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

    @Dimension
    var profileIconMarginStartDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension
    var profileIconMarginEndDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension
    var profileIconMarginTopDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension
    var profileIconMarginBottomDp: Int = DEFAULT_BIG_MARGIN_DP

    var interlocutorNameAlignment: MessageTextAlignment = MessageTextAlignment.START
    var lastMessageDateAlignment: MessageTextAlignment = MessageTextAlignment.END

    var showOnlineStatus: Boolean = true
    var showProfileIcon: Boolean = true
    var showLastMessageDate: Boolean = true

    var lastMessageDateFormat: MessageDateFormat = MessageDateFormat.SHOW_ONLY_TIME
}
