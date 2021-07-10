package com.levit.bookme.chatkit.models.current_chat_header

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment

class CurrentChatHeaderStyleOptions(
    build: CurrentChatHeaderStyleOptionsBuilder.() -> CurrentChatHeaderStyleOptionsBuilder
) {

    companion object {

        fun provideDefaultOptions(): CurrentChatHeaderStyleOptions {
            return CurrentChatHeaderStyleOptions {
                return@CurrentChatHeaderStyleOptions this
            }
        }
    }

    @ColorInt
    val layoutBackgroundColor: Int
    @ColorInt
    val layoutBackgroundStrokeColor: Int
    @ColorInt
    val interlocutorNameTextColor: Int
    @ColorInt
    val additionalTextColor: Int

    @Dimension(unit = Dimension.DP)
    var layoutBackgroundStrokeWidthDp: Int

    @Dimension(unit = Dimension.DP)
    val layoutBackgroundTopLeftRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutBackgroundTopRightRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutBackgroundBottomLeftRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutBackgroundBottomRightRadiusDp: Int

    @Dimension(unit = Dimension.DP)
    val backButtonMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val backButtonMarginEndDp: Int
    @Dimension(unit = Dimension.DP)
    val backButtonMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val backButtonMarginBottomDp: Int

    @Dimension(unit = Dimension.DP)
    val backButtonWidthDp: Int
    @Dimension(unit = Dimension.DP)
    val backButtonHeightDp: Int

    @Dimension(unit = Dimension.DP)
    val profileIconMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val profileIconMarginBottomDp: Int
    @Dimension(unit = Dimension.DP)
    val profileIconMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val profileIconMarginEndDp: Int

    @Dimension(unit = Dimension.DP)
    val interlocutorNameMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val interlocutorNameMarginBottomDp: Int
    @Dimension(unit = Dimension.DP)
    val interlocutorNameMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val interlocutorNameMarginEndDp: Int

    @Dimension(unit = Dimension.DP)
    val additionalTextMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val additionalTextMarginBottomDp: Int
    @Dimension(unit = Dimension.DP)
    val additionalTextMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val additionalTextMarginEndDp: Int

    @Dimension(unit = Dimension.SP)
    val interlocutorNameTextSizeSp: Int
    @Dimension(unit = Dimension.SP)
    val additionalTextSizeSp: Int

    val interlocutorNameAlignment: MessageTextAlignment
    val additionalTextAlignment: MessageTextAlignment

    val showAdditionalText: Boolean
    val showOnlineStatus: Boolean

    init {
        val options = CurrentChatHeaderStyleOptionsBuilder().build()

        this.layoutBackgroundColor = options.layoutBackgroundColor
        this.layoutBackgroundStrokeColor = options.layoutBackgroundStrokeColor
        this.interlocutorNameTextColor = options.interlocutorNameTextColor
        this.additionalTextColor = options.additionalTextColor

        this.layoutBackgroundStrokeWidthDp = options.layoutBackgroundStrokeWidthDp

        this.layoutBackgroundTopLeftRadiusDp = options.layoutBackgroundTopLeftRadiusDp
        this.layoutBackgroundTopRightRadiusDp = options.layoutBackgroundTopRightRadiusDp
        this.layoutBackgroundBottomLeftRadiusDp = options.layoutBackgroundBottomLeftRadiusDp
        this.layoutBackgroundBottomRightRadiusDp = options.layoutBackgroundBottomRightRadiusDp

        this.backButtonMarginStartDp = options.backButtonMarginStartDp
        this.backButtonMarginEndDp = options.backButtonMarginEndDp
        this.backButtonMarginTopDp = options.backButtonMarginTopDp
        this.backButtonMarginBottomDp = options.backButtonMarginBottomDp

        this.backButtonWidthDp = options.backButtonWidthDp
        this.backButtonHeightDp = options.backButtonHeightDp

        this.profileIconMarginTopDp = options.profileIconMarginTopDp
        this.profileIconMarginBottomDp = options.profileIconMarginBottomDp
        this.profileIconMarginStartDp = options.profileIconMarginStartDp
        this.profileIconMarginEndDp = options.profileIconMarginEndDp

        this.interlocutorNameMarginTopDp = options.interlocutorNameMarginTopDp
        this.interlocutorNameMarginBottomDp = options.interlocutorNameMarginBottomDp
        this.interlocutorNameMarginStartDp = options.interlocutorNameMarginStartDp
        this.interlocutorNameMarginEndDp = options.interlocutorNameMarginEndDp

        this.additionalTextMarginTopDp = options.additionalTextMarginTopDp
        this.additionalTextMarginBottomDp = options.additionalTextMarginBottomDp
        this.additionalTextMarginStartDp = options.additionalTextMarginStartDp
        this.additionalTextMarginEndDp = options.additionalTextMarginEndDp

        this.interlocutorNameTextSizeSp = options.interlocutorNameTextSizeSp
        this.additionalTextSizeSp = options.additionalTextSizeSp

        this.interlocutorNameAlignment = options.interlocutorNameAlignment
        this.additionalTextAlignment = options.additionalTextAlignment

        this.showAdditionalText = options.showAdditionalText
        this.showOnlineStatus = options.showOnlineStatus
    }
}