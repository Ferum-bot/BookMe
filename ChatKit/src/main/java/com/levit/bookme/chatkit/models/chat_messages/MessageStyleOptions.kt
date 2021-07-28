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
    val backgroundTextColor: Int
    @ColorInt
    val authorColor: Int
    @ColorInt
    val dateColor: Int

    @ColorInt
    val messageBackgroundColor: Int
    @ColorInt
    val messageBackgroundStrokeColor: Int

    @Dimension(unit = Dimension.DP)
    val messageBackgroundStrokeWidthDP: Int

    @Dimension(unit = Dimension.DP)
    val messageBackgroundTopLeftCornerRadiusDP: Int
    @Dimension(unit = Dimension.DP)
    val messageBackgroundTopRightCornerRadiusDP: Int
    @Dimension(unit = Dimension.DP)
    val messageBackgroundBottomLeftCornerRadiusDP: Int
    @Dimension(unit = Dimension.DP)
    val messageBackgroundBottomRightCornerRadiusDP: Int

    val authorLabelAlignment: MessageTextAlignment
    val dateLabelAlignment: MessageTextAlignment

    val dateShowFormat: MessageDateFormat

    @Dimension(unit = Dimension.SP)
    val textSizeSP: Int
    @Dimension(unit = Dimension.SP)
    val authorLabelSizeSP: Int
    @Dimension(unit = Dimension.SP)
    val dateLabelSizeSP: Int

    @Dimension(unit = Dimension.DP)
    val textMarginTopDP: Int
    @Dimension(unit = Dimension.DP)
    val textMarginStartDP: Int
    @Dimension(unit = Dimension.DP)
    val textMarginEndDP: Int
    @Dimension(unit = Dimension.DP)
    val textMarginBottom: Int

    @Dimension(unit = Dimension.DP)
    val authorLabelMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val authorLabelMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val authorLabelMarginEndDp: Int
    @Dimension(unit = Dimension.DP)
    val authorLabelMarginBottomDp: Int

    @Dimension(unit = Dimension.DP)
    val dateLabelMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val dateLabelMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val dateLabelMarginEndDp: Int
    @Dimension(unit = Dimension.DP)
    val dateLabelMarginBottomDp: Int

    val showAuthorLabel: Boolean
    val showDateLabel: Boolean
    val showProfileImage: Boolean

    init {
        val options = MessageStyleOptionsBuilder().build()

        this.textColor = options.textColor
        this.backgroundTextColor = options.backgroundTextColor
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

        this.authorLabelMarginTopDp = options.authorLabelMarginTop
        this.authorLabelMarginStartDp = options.authorLabelMarginStart
        this.authorLabelMarginEndDp = options.authorLabelMarginEnd
        this.authorLabelMarginBottomDp = options.authorLabelMarginBottom

        this.dateLabelMarginTopDp = options.dateLabelMarginTop
        this.dateLabelMarginStartDp = options.dateLabelMarginStart
        this.dateLabelMarginEndDp = options.dateLabelMarginEnd
        this.dateLabelMarginBottomDp = options.dateLabelMarginBottom

        this.showAuthorLabel = options.showAuthorLabel
        this.showDateLabel = options.showDateLabel
        this.showProfileImage = options.showProfileImage
    }

}