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

    @Dimension(unit = Dimension.DP)
    var backgroundStrokeWidthDp: Int = DEFAULT_STROKE_WIDTH_DP

    @Dimension(unit = Dimension.DP)
    var backgroundTopLeftRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var backgroundTopRightRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var backgroundBottomLeftRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var backgroundBottomRightRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP

    @Dimension(unit = Dimension.SP)
    var interlocutorNameTextSizeSp: Int = DEFAULT_BIG_TEXT_SIZE_SP
    @Dimension(unit = Dimension.SP)
    var lastMessageTextSizeSp: Int = DEFAULT_SMALL_TEXT_SIZE_SP
    @Dimension(unit = Dimension.SP)
    var lastMessageDateTextSizeSp: Int = DEFAULT_SMALL_TEXT_SIZE_SP

    @Dimension(unit = Dimension.DP)
    var interlocutorNameMarginTopDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var interlocutorNameMarginBottomDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var interlocutorNameMarginStartDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var interlocutorNameMarginEndDp: Int = DEFAULT_BIG_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var lastMessageMarginStartDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var lastMessageMarginEndDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var lastMessageMarginTopDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var lastMessageMarginBottomDp: Int = DEFAULT_SMALL_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var lastMessageDateMarginStartDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var lastMessageDateMarginEndDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var lastMessageDateMarginTopDp: Int = DEFAULT_SMALL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var lastMessageDateMarginBottomDp: Int = DEFAULT_SMALL_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var profileIconMarginStartDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var profileIconMarginEndDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var profileIconMarginTopDp: Int = DEFAULT_BIG_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var profileIconMarginBottomDp: Int = DEFAULT_BIG_MARGIN_DP

    var interlocutorNameAlignment: MessageTextAlignment = MessageTextAlignment.START
    var lastMessageDateAlignment: MessageTextAlignment = MessageTextAlignment.END

    var showOnlineStatus: Boolean = true
    var showProfileIcon: Boolean = true
    var showLastMessageDate: Boolean = true

    var lastMessageDateFormat: MessageDateFormat = MessageDateFormat.SHOW_ONLY_TIME
}
