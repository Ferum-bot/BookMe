package com.levit.bookme.chatkit.models.chat

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

    @Dimension
    val interlocutorNameTextSizeSp: Int
    @Dimension
    val lastMessageTextSizeSp: Int
    @Dimension
    val lastMessageDateTextSizeSp: Int

    @Dimension
    val interlocutorNameMarginTopDp: Int
    @Dimension
    val interlocutorNameMarginBottomDp: Int
    @Dimension
    val interlocutorNameMarginStartDp: Int
    @Dimension
    val interlocutorNameMarginEndDp: Int

    @Dimension
    val lastMessageMarginStartDp: Int
    @Dimension
    val lastMessageMarginEndDp: Int
    @Dimension
    val lastMessageMarginTopDp: Int
    @Dimension
    val lastMessageMarginBottomDp: Int

    @Dimension
    val lastMessageDateMarginStartDp: Int
    @Dimension
    val lastMessageDateMarginEndDp: Int
    @Dimension
    val lastMessageDateMarginTopDp: Int
    @Dimension
    val lastMessageDateMarginBottomDp: Int

    @Dimension
    val profileIconMarginStartDp: Int
    @Dimension
    val profileIconMarginEndDp: Int
    @Dimension
    val profileIconMarginTopDp: Int
    @Dimension
    val profileIconMarginBottomDp: Int

    val interlocutorNameAlignment: MessageTextAlignment
    val lastMessageDateAlignment: MessageTextAlignment

    val showOnlineStatus: Boolean
    val showProfileIcon: Boolean
    val showLastMessageDate: Boolean

    val lastMessageDateFormat: MessageDateFormat

    init {
        val options = ChatStyleOptionsBuilder().build()

        this.interlocutorNameTextColor = options.interlocutorNameTextColor
        this.lastMessageTextColor = options.lastMessageTextColor
        this.lastMessageDateTextColor = options.lastMessageDateTextColor

        this.interlocutorNameTextSizeSp = options.interlocutorNameTextSizeSp
        this.lastMessageTextSizeSp = options.lastMessageTextSizeSp
        this.lastMessageDateTextSizeSp = options.lastMessageTextSizeSp

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

        this.lastMessageDateFormat = options.lastMessageDateFormat
    }

}