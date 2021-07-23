package com.levit.bookme.chatkit.models.chat

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.MessageDateFormat
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment

class ChatStyleOptions(
    build: ChatStyleOptionsBuilder.() -> ChatStyleOptionsBuilder
) {

    companion object {

        fun provideDefaultStyleOptions() = ChatStyleOptions {
            return@ChatStyleOptions this
        }
    }

    @ColorInt
    val interlocutorNameTextColor: Int
    @ColorInt
    val lastMessageTextColor: Int
    @ColorInt
    val lastMessageDateTextColor: Int
    @ColorInt
    val unReadChatMessagesTextColor: Int
    @ColorInt
    val unReadChatMessagesBackgroundColor: Int
    @ColorInt
    val unReadChatMessagesStrokeColor: Int
    @ColorInt
    val backgroundColor: Int
    @ColorInt
    val backgroundStrokeColor: Int

    @Dimension(unit = Dimension.DP)
    val backgroundStrokeWidthDp: Int
    @Dimension(unit = Dimension.DP)
    val unReadChatMessagesStrokeWidthDp: Int

    @Dimension(unit = Dimension.DP)
    val backgroundTopLeftRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val backgroundTopRightRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val backgroundBottomLeftRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val backgroundBottomRightRadiusDp: Int

    @Dimension(unit = Dimension.SP)
    val interlocutorNameTextSizeSp: Int
    @Dimension(unit = Dimension.SP)
    val lastMessageTextSizeSp: Int
    @Dimension(unit = Dimension.SP)
    val lastMessageDateTextSizeSp: Int
    @Dimension(unit = Dimension.SP)
    val unReadChatMessagesTextSizeSp: Int

    @Dimension(unit = Dimension.DP)
    val interlocutorNameMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val interlocutorNameMarginBottomDp: Int
    @Dimension(unit = Dimension.DP)
    val interlocutorNameMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val interlocutorNameMarginEndDp: Int

    @Dimension(unit = Dimension.DP)
    val lastMessageMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val lastMessageMarginEndDp: Int
    @Dimension(unit = Dimension.DP)
    val lastMessageMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val lastMessageMarginBottomDp: Int

    @Dimension(unit = Dimension.DP)
    val lastMessageDateMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val lastMessageDateMarginEndDp: Int
    @Dimension(unit = Dimension.DP)
    val lastMessageDateMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val lastMessageDateMarginBottomDp: Int

    @Dimension(unit = Dimension.DP)
    val profileIconMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val profileIconMarginEndDp: Int
    @Dimension(unit = Dimension.DP)
    val profileIconMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val profileIconMarginBottomDp: Int

    val interlocutorNameAlignment: MessageTextAlignment
    val lastMessageDateAlignment: MessageTextAlignment

    val showOnlineStatus: Boolean
    val showProfileIcon: Boolean
    val showLastMessageDate: Boolean
    val showNumberOfUnreadMessages: Boolean

    val lastMessageDateFormat: MessageDateFormat

    init {
        val options = ChatStyleOptionsBuilder().build()

        this.interlocutorNameTextColor = options.interlocutorNameTextColor
        this.lastMessageTextColor = options.lastMessageTextColor
        this.lastMessageDateTextColor = options.lastMessageDateTextColor
        this.unReadChatMessagesTextColor = options.unReadChatMessagesTextColor
        this.unReadChatMessagesBackgroundColor = options.unReadChatMessagesBackgroundColor
        this.unReadChatMessagesStrokeColor = options.unReadChatMessagesStrokeColor
        this.backgroundColor = options.backgroundColor
        this.backgroundStrokeColor = options.backgroundStrokeColor

        this.backgroundStrokeWidthDp = options.backgroundStrokeWidthDp
        this.unReadChatMessagesStrokeWidthDp = options.unReadChatMessagesStrokeWidthDp

        this.backgroundTopLeftRadiusDp = options.backgroundTopLeftRadiusDp
        this.backgroundTopRightRadiusDp = options.backgroundTopRightRadiusDp
        this.backgroundBottomLeftRadiusDp = options.backgroundBottomLeftRadiusDp
        this.backgroundBottomRightRadiusDp = options.backgroundBottomRightRadiusDp

        this.interlocutorNameTextSizeSp = options.interlocutorNameTextSizeSp
        this.lastMessageTextSizeSp = options.lastMessageTextSizeSp
        this.lastMessageDateTextSizeSp = options.lastMessageDateTextSizeSp
        this.unReadChatMessagesTextSizeSp = options.unReadChatMessagesTextSizeSp

        this.interlocutorNameMarginTopDp = options.interlocutorNameMarginTopDp
        this.interlocutorNameMarginBottomDp = options.interlocutorNameMarginBottomDp
        this.interlocutorNameMarginStartDp = options.interlocutorNameMarginStartDp
        this.interlocutorNameMarginEndDp = options.interlocutorNameMarginEndDp

        this.lastMessageMarginStartDp = options.lastMessageMarginStartDp
        this.lastMessageMarginEndDp = options.lastMessageMarginEndDp
        this.lastMessageMarginTopDp = options.lastMessageMarginTopDp
        this.lastMessageMarginBottomDp = options.lastMessageMarginBottomDp

        this.lastMessageDateMarginStartDp = options.lastMessageDateMarginStartDp
        this.lastMessageDateMarginEndDp = options.lastMessageDateMarginEndDp
        this.lastMessageDateMarginTopDp = options.lastMessageDateMarginTopDp
        this.lastMessageDateMarginBottomDp = options.lastMessageDateMarginBottomDp

        this.profileIconMarginStartDp = options.profileIconMarginStartDp
        this.profileIconMarginEndDp = options.profileIconMarginEndDp
        this.profileIconMarginTopDp = options.profileIconMarginTopDp
        this.profileIconMarginBottomDp = options.profileIconMarginBottomDp

        this.interlocutorNameAlignment = options.interlocutorNameAlignment
        this.lastMessageDateAlignment = options.lastMessageDateAlignment

        this.showOnlineStatus = options.showOnlineStatus
        this.showProfileIcon = options.showProfileIcon
        this.showLastMessageDate = options.showLastMessageDate
        this.showNumberOfUnreadMessages = options.showNumberOfUnreadMessages

        this.lastMessageDateFormat = options.lastMessageDateFormat
    }

}