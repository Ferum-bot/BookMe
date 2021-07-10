package com.levit.bookme.chatkit.models.message_input

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension

class MessageInputStyleOptions(
    build: MessageInputStyleOptionsBuilder.() -> MessageInputStyleOptionsBuilder
) {

    companion object {

        fun provideDefaultStyleOptions(): MessageInputStyleOptions {
            return MessageInputStyleOptions {
                return@MessageInputStyleOptions this
            }
        }
    }

    @ColorInt
    val layoutBackgroundColor: Int
    @ColorInt
    val layoutBackgroundStrokeColor: Int
    @ColorInt
    val textInputBackgroundColor: Int
    @ColorInt
    val textInputBackgroundStrokeColor: Int
    @ColorInt
    val textInputColor: Int
    @ColorInt
    val textHintInputColor: Int

    @Dimension(unit = Dimension.SP)
    val textInputTextSizeSp: Int

    @Dimension(unit = Dimension.DP)
    val layoutBackgroundStrokeWidthDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputBackgroundStrokeWidthDp: Int

    @Dimension(unit = Dimension.DP)
    val textInputBackgroundTopLeftRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputBackgroundTopRightRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputBackgroundBottomLeftRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputBackgroundBottomRightRadiusDp: Int

    @Dimension(unit = Dimension.DP)
    val layoutBackgroundTopLeftRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutBackgroundTopRightRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutBackgroundBottomLeftRadiusDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutBackgroundBottomRightRadiusDp: Int

    @Dimension(unit = Dimension.DP)
    val textInputMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputMarginEndDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputMarginBottomDp: Int

    @Dimension(unit = Dimension.DP)
    val textInputPaddingTopDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputPaddingStartDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputPaddingEndDp: Int
    @Dimension(unit = Dimension.DP)
    val textInputPaddingBottomDp: Int

    @Dimension(unit = Dimension.DP)
    val sendButtonWidthDp: Int
    @Dimension(unit = Dimension.DP)
    val sendButtonHeightDp: Int

    @Dimension(unit = Dimension.DP)
    val sendButtonMarginTopDp: Int
    @Dimension(unit = Dimension.DP)
    val sendButtonMarginStartDp: Int
    @Dimension(unit = Dimension.DP)
    val sendButtonMarginEndDp: Int
    @Dimension(unit = Dimension.DP)
    val sendButtonMarginBottomDp: Int

    init {
        val options = MessageInputStyleOptionsBuilder().build()

        this.layoutBackgroundColor = options.layoutBackgroundColor
        this.layoutBackgroundStrokeColor = options.layoutBackgroundStrokeColor
        this.textInputBackgroundColor = options.textInputBackgroundColor
        this.textInputBackgroundStrokeColor = options.textInputBackgroundStrokeColor
        this.textInputColor = options.textInputColor
        this.textHintInputColor = options.textHintInputColor

        this.textInputTextSizeSp = options.textInputTextSizeSp

        this.layoutBackgroundStrokeWidthDp = options.layoutBackgroundStrokeWidthDp
        this.textInputBackgroundStrokeWidthDp = options.textInputBackgroundStrokeWidthDp

        this.textInputBackgroundTopLeftRadiusDp = options.textInputBackgroundTopLeftRadiusDp
        this.textInputBackgroundTopRightRadiusDp = options.textInputBackgroundTopRightRadiusDp
        this.textInputBackgroundBottomLeftRadiusDp = options.textInputBackgroundBottomLeftRadiusDp
        this.textInputBackgroundBottomRightRadiusDp = options.textInputBackgroundBottomRightRadiusDp

        this.layoutBackgroundTopLeftRadiusDp = options.layoutBackgroundTopLeftRadiusDp
        this.layoutBackgroundTopRightRadiusDp = options.layoutBackgroundTopRightRadiusDp
        this.layoutBackgroundBottomLeftRadiusDp = options.layoutBackgroundBottomLeftRadiusDp
        this.layoutBackgroundBottomRightRadiusDp = options.layoutBackgroundBottomRightRadiusDp

        this.textInputMarginTopDp = options.textInputMarginTopDp
        this.textInputMarginStartDp = options.textInputMarginStartDp
        this.textInputMarginEndDp = options.textInputMarginEndDp
        this.textInputMarginBottomDp = options.textInputMarginBottomDp

        this.textInputPaddingTopDp = options.textInputPaddingTopDp
        this.textInputPaddingStartDp = options.textInputPaddingStartDp
        this.textInputPaddingEndDp = options.textInputPaddingEndDp
        this.textInputPaddingBottomDp = options.textInputPaddingBottomDp

        this.sendButtonWidthDp = options.sendButtonWidthDp
        this.sendButtonHeightDp = options.sendButtonHeightDp

        this.sendButtonMarginTopDp = options.sendButtonMarginTopDp
        this.sendButtonMarginStartDp = options.sendButtonMarginStartDp
        this.sendButtonMarginEndDp = options.sendButtonMarginEndDp
        this.sendButtonMarginBottomDp = options.sendButtonMarginBottomDp
    }
}