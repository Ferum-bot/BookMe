package com.levit.bookme.chatkit.models.current_chat_feed

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.ChatFeedEmptyMessageFormat

class CurrentChatFeedStyleOptions(
    build: CurrentChatFeedStyleOptionsBuilder.() -> CurrentChatFeedStyleOptionsBuilder
) {

    companion object {

        fun provideDefaultOptions(): CurrentChatFeedStyleOptions {
            return CurrentChatFeedStyleOptions {
                return@CurrentChatFeedStyleOptions this
            }
        }
    }

    @ColorInt
    val messagesLayoutBackgroundColor: Int
    @ColorInt
    val emptyChatFeedTextMessageColor: Int

    val emptyChatFeedFormat: ChatFeedEmptyMessageFormat

    @Dimension(unit = Dimension.SP)
    val emptyChatFeedTextMessageSizeSp: Int

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
        this.emptyChatFeedTextMessageColor = options.emptyChatFeedTextMessageColor

        this.emptyChatFeedFormat = options.emptyChatFeedFormat

        this.emptyChatFeedTextMessageSizeSp = options.emptyChatFeedTextMessageSizeSp

        this.messagesLayoutExtraSpaceTopDp = options.messagesLayoutExtraSpaceTopDp
        this.messagesLayoutExtraSpaceStartDp = options.messagesLayoutExtraSpaceStartDp
        this.messagesLayoutExtraSpaceEndDp = options.messagesLayoutExtraSpaceEndDp
        this.messagesLayoutExtraSpaceBottomDp = options.messagesLayoutExtraSpaceBottomDp
    }
}