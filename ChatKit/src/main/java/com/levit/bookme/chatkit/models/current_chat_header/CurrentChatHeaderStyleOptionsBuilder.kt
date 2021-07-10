package com.levit.bookme.chatkit.models.current_chat_header

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment

class CurrentChatHeaderStyleOptionsBuilder {

    companion object {

        private const val DEFAULT_BACKGROUND_RADIUS_DP = 0
        private const val DEFAULT_BACKGROUND_STROKE_WIDTH_DP = 1

        private const val DEFAULT_BACK_BUTTON_MARGIN_DP = 8
        private const val DEFAULT_BACK_BUTTON_SIZE_DP = 25

        private const val DEFAULT_PROFILE_ICON_MARGIN_DP = 8

        private const val DEFAULT_INTERLOCUTOR_MARGIN_DP = 8

        private const val DEFAULT_ADDITIONAL_TEXT_MARGIN_VERTICAL_DP = 0
        private const val DEFAULT_ADDITIONAL_TEXT_MARGIN_HORIZONTAL_DP = 8

        private const val DEFAULT_BIG_TEXT_SIZE_SP = 16
        private const val DEFAULT_SMALL_TEXT_SIZE_SP = 12
    }

    @ColorInt
    var layoutBackgroundColor: Int = Color.WHITE
    @ColorInt
    var layoutBackgroundStrokeColor: Int = Color.GRAY
    @ColorInt
    var interlocutorNameTextColor: Int = Color.BLACK
    @ColorInt
    var additionalTextColor: Int = Color.LTGRAY

    @Dimension(unit = Dimension.DP)
    var layoutBackgroundStrokeWidthDp: Int = DEFAULT_BACKGROUND_STROKE_WIDTH_DP

    @Dimension(unit = Dimension.DP)
    var layoutBackgroundTopLeftRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var layoutBackgroundTopRightRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var layoutBackgroundBottomLeftRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var layoutBackgroundBottomRightRadiusDp: Int = DEFAULT_BACKGROUND_RADIUS_DP

    @Dimension(unit = Dimension.DP)
    var backButtonMarginStartDp: Int = DEFAULT_BACK_BUTTON_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var backButtonMarginEndDp: Int = DEFAULT_BACK_BUTTON_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var backButtonMarginTopDp: Int = DEFAULT_BACK_BUTTON_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var backButtonMarginBottomDp: Int = DEFAULT_BACK_BUTTON_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var backButtonWidthDp: Int = DEFAULT_BACK_BUTTON_SIZE_DP
    @Dimension(unit = Dimension.DP)
    var backButtonHeightDp: Int = DEFAULT_BACK_BUTTON_SIZE_DP

    @Dimension(unit = Dimension.DP)
    var profileIconMarginTopDp: Int = DEFAULT_PROFILE_ICON_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var profileIconMarginBottomDp: Int = DEFAULT_PROFILE_ICON_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var profileIconMarginStartDp: Int = DEFAULT_PROFILE_ICON_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var profileIconMarginEndDp: Int = DEFAULT_PROFILE_ICON_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var interlocutorNameMarginTopDp: Int = DEFAULT_INTERLOCUTOR_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var interlocutorNameMarginBottomDp: Int = DEFAULT_INTERLOCUTOR_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var interlocutorNameMarginStartDp: Int = DEFAULT_INTERLOCUTOR_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var interlocutorNameMarginEndDp: Int = DEFAULT_INTERLOCUTOR_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var additionalTextMarginTopDp: Int = DEFAULT_ADDITIONAL_TEXT_MARGIN_VERTICAL_DP
    @Dimension(unit = Dimension.DP)
    var additionalTextMarginBottomDp: Int = DEFAULT_ADDITIONAL_TEXT_MARGIN_VERTICAL_DP
    @Dimension(unit = Dimension.DP)
    var additionalTextMarginStartDp: Int = DEFAULT_ADDITIONAL_TEXT_MARGIN_HORIZONTAL_DP
    @Dimension(unit = Dimension.DP)
    var additionalTextMarginEndDp: Int = DEFAULT_ADDITIONAL_TEXT_MARGIN_HORIZONTAL_DP

    @Dimension(unit = Dimension.SP)
    var interlocutorNameTextSizeSp: Int = DEFAULT_BIG_TEXT_SIZE_SP
    @Dimension(unit = Dimension.SP)
    var additionalTextSizeSp: Int = DEFAULT_SMALL_TEXT_SIZE_SP

    var interlocutorNameAlignment: MessageTextAlignment = MessageTextAlignment.START
    var additionalTextAlignment: MessageTextAlignment = MessageTextAlignment.START

    var showAdditionalText: Boolean = true
    var showOnlineStatus: Boolean = false
}