package com.levit.bookme.chatkit.models.chat_messages

import android.graphics.Color
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
    @ColorInt
    val messageBackgroundStrokeColor: Int

    @Dimension
    val messageBackgroundStrokeWidthDP: Int

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

        this.textColor = options.textColor
        this.authorColor = options.authorColor
        this.dateColor = options.dateColor

        this.messageBackgroundColor = options.messageBackgroundColor
        this.messageBackgroundStrokeColor = options.messageBackgroundStrokeColor

        this.messageBackgroundStrokeWidthDP = options.messageBackgroundStrokeWidthDP

        this.messageBackgroundTopLeftCornerRadiusDP = options.messageBackgroundTopLeftCornerRadiusDP
        this.messageBackgroundTopRightCornerRadiusDP = options.messageBackgroundTopRightCornerRadiusDP
        this.messageBackgroundBottomLeftCornerRadiusDP = options.messageBackgroundBottomLeftCornerRadiusDP
        this.messageBackgroundBottomRightCornerRadiusDP = options.messageBackgroundBottomRightCornerRadiusDP

        this.authorLabelAlignment = options.authorLabelAlignment
        this.dateLabelAlignment = options.dateLabelAlignment

        this.dateShowFormat = options.dateShowFormat

        this.textSizeSP = options.textSizeSP
        this.authorLabelSizeSP = options.authorLabelSizeSP
        this.dateLabelSizeSP = options.dateLabelSizeSP

        this.textMarginTopDP = options.textMarginTopDP
        this.textMarginStartDP = options.textMarginStartDP
        this.textMarginEndDP = options.textMarginEndDP
        this.textMarginBottom = options.textMarginBottom

        this.authorLabelMarginTop = options.authorLabelMarginTop
        this.authorLabelMarginStart = options.authorLabelMarginStart
        this.authorLabelMarginEnd = options.authorLabelMarginEnd
        this.authorLabelMarginBottom = options.authorLabelMarginBottom

        this.dateLabelMarginTop = options.dateLabelMarginTop
        this.dateLabelMarginStart = options.dateLabelMarginStart
        this.dateLabelMarginEnd = options.dateLabelMarginEnd
        this.dateLabelMarginBottom = options.dateLabelMarginBottom

        this.showAuthorLabel = options.showAuthorLabel
        this.showDateLabel = options.showDateLabel
        this.showProfileImage = options.showProfileImage
    }

}