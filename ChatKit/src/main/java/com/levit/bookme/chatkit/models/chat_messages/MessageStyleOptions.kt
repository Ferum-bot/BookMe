package com.levit.bookme.chatkit.models.chat_messages

import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.MessageDateFormat
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment

class MessageStyleOptions(
    build: MessageStyleOptionsBuilder.() -> MessageStyleOptionsBuilder,
) {

    companion object {

        fun provideDefaultStyleOptions() = MessageStyleOptions {
            return@MessageStyleOptions this
        }
    }

    @ColorInt
    val textColor: Int
    @ColorInt
    val authorColor: Int
    @ColorInt
    val dateColor: Int

    @ColorInt
    val messageBackgroundColor: Int

    @Dimension
    val messageBackgroundTopLeftCornerRadiusDP: Int
    @Dimension
    val messageBackgroundTopRightCornerRadiusDP: Int
    @Dimension
    val messageBackgroundBottomLeftCornerRadiusDP: Int
    @Dimension
    val messageBackgroundBottomRightCornerRadiusDP: Int

    val authorLabelAlignment: MessageTextAlignment
    val dateLabelAlignment: MessageTextAlignment

    val dateShowFormat: MessageDateFormat

    @Dimension
    val textSizeSP: Int
    @Dimension
    val authorLabelSizeSP: Int
    @Dimension
    val dateLabelSizeSP: Int

    @Dimension
    val textMarginTopDP: Int
    @Dimension
    val textMarginStartDP: Int
    @Dimension
    val textMarginEndDP: Int
    @Dimension
    val textMarginBottom: Int

    @Dimension
    val authorLabelMarginTop: Int
    @Dimension
    val authorLabelMarginStart: Int
    @Dimension
    val authorLabelMarginEnd: Int
    @Dimension
    val authorLabelMarginBottom: Int

    @Dimension
    val dateLabelMarginTop: Int
    @Dimension
    val dateLabelMarginStart: Int
    @Dimension
    val dateLabelMarginEnd: Int
    @Dimension
    val dateLabelMarginBottom: Int

    val showAuthorLabel: Boolean
    val showDateLabel: Boolean
    val showProfileImage: Boolean

    init {
        val options = MessageStyleOptionsBuilder().build()

        textColor = options.textColor
        authorColor = options.authorColor
        dateColor = options.dateColor

        messageBackgroundColor = options.messageBackgroundColor

        messageBackgroundTopLeftCornerRadiusDP = options.messageBackgroundTopLeftCornerRadiusDP
        messageBackgroundTopRightCornerRadiusDP = options.messageBackgroundTopRightCornerRadiusDP
        messageBackgroundBottomLeftCornerRadiusDP = options.messageBackgroundBottomLeftCornerRadiusDP
        messageBackgroundBottomRightCornerRadiusDP = options.messageBackgroundBottomRightCornerRadiusDP

        authorLabelAlignment = options.authorLabelAlignment
        dateLabelAlignment = options.dateLabelAlignment

        dateShowFormat = options.dateShowFormat

        textSizeSP = options.textSizeSP
        authorLabelSizeSP = options.authorLabelSizeSP
        dateLabelSizeSP = options.dateLabelSizeSP

        textMarginTopDP = options.textMarginTopDP
        textMarginStartDP = options.textMarginStartDP
        textMarginEndDP = options.textMarginEndDP
        textMarginBottom = options.textMarginBottom

        authorLabelMarginTop = options.authorLabelMarginTop
        authorLabelMarginStart = options.authorLabelMarginStart
        authorLabelMarginEnd = options.authorLabelMarginEnd
        authorLabelMarginBottom = options.authorLabelMarginBottom

        dateLabelMarginTop = options.dateLabelMarginTop
        dateLabelMarginStart = options.dateLabelMarginStart
        dateLabelMarginEnd = options.dateLabelMarginEnd
        dateLabelMarginBottom = options.dateLabelMarginBottom

        showAuthorLabel = options.showAuthorLabel
        showDateLabel = options.showDateLabel
        showProfileImage = options.showProfileImage
    }

}