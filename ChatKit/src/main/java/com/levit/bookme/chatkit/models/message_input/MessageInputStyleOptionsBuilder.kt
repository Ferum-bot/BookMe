package com.levit.bookme.chatkit.models.message_input

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension

class MessageInputStyleOptionsBuilder {

    companion object {

        private const val DEFAULT_BACKGROUND_STROKE_WIDTH_DP = 1
        private const val DEFAULT_BACKGROUND_CORNER_RADIUS_DP = 0

        private const val DEFAULT_TEXT_INPUT_CORNER_RADIUS_DP = 0
        private const val DEFAULT_TEXT_INPUT_MARGIN_DP = 0
        private const val DEFAULT_TEXT_INPUT_BACKGROUND_STROKE_WIDTH_DP = 0
        private const val DEFAULT_TEXT_INPUT_PADDING_START_DP = 8
        private const val DEFAULT_TEXT_INPUT_PADDING_DP = 0

        private const val DEFAULT_TEXT_INPUT_TEXT_SIZE_SP = 18

        private const val DEFAULT_SEND_BUTTON_SIZE_DP = 25
        private const val DEFAULT_SEND_BUTTON_MARGIN_END_DP = 8
        private const val DEFAULT_SEND_BUTTON_MARGIN_DP = 0
    }

    @ColorInt
    var layoutBackgroundColor: Int = Color.WHITE
    @ColorInt
    var layoutBackgroundStrokeColor: Int = Color.GRAY
    @ColorInt
    var textInputBackgroundColor: Int = Color.WHITE
    @ColorInt
    var textInputBackgroundStrokeColor: Int = Color.WHITE
    @ColorInt
    var textInputColor: Int = Color.BLACK
    @ColorInt
    var textHintInputColor: Int = Color.GRAY

    @Dimension(unit = Dimension.SP)
    var textInputTextSizeSp: Int = DEFAULT_TEXT_INPUT_TEXT_SIZE_SP

    @Dimension(unit = Dimension.DP)
    var layoutBackgroundStrokeWidthDp: Int = DEFAULT_BACKGROUND_STROKE_WIDTH_DP
    @Dimension(unit = Dimension.DP)
    var textInputBackgroundStrokeWidthDp: Int = DEFAULT_TEXT_INPUT_BACKGROUND_STROKE_WIDTH_DP

    @Dimension(unit = Dimension.DP)
    var textInputBackgroundTopLeftRadiusDp: Int = DEFAULT_TEXT_INPUT_CORNER_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var textInputBackgroundTopRightRadiusDp: Int = DEFAULT_TEXT_INPUT_CORNER_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var textInputBackgroundBottomLeftRadiusDp: Int = DEFAULT_TEXT_INPUT_CORNER_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var textInputBackgroundBottomRightRadiusDp: Int = DEFAULT_TEXT_INPUT_CORNER_RADIUS_DP

    @Dimension(unit = Dimension.DP)
    var layoutBackgroundTopLeftRadiusDp: Int = DEFAULT_BACKGROUND_CORNER_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var layoutBackgroundTopRightRadiusDp: Int = DEFAULT_BACKGROUND_CORNER_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var layoutBackgroundBottomLeftRadiusDp: Int = DEFAULT_BACKGROUND_CORNER_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var layoutBackgroundBottomRightRadiusDp: Int = DEFAULT_BACKGROUND_CORNER_RADIUS_DP

    @Dimension(unit = Dimension.DP)
    var textInputMarginTopDp: Int = DEFAULT_TEXT_INPUT_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var textInputMarginStartDp: Int = DEFAULT_TEXT_INPUT_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var textInputMarginEndDp: Int = DEFAULT_TEXT_INPUT_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var textInputMarginBottomDp: Int = DEFAULT_TEXT_INPUT_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var textInputPaddingTopDp = DEFAULT_TEXT_INPUT_PADDING_DP
    @Dimension(unit = Dimension.DP)
    var textInputPaddingStartDp = DEFAULT_TEXT_INPUT_PADDING_START_DP
    @Dimension(unit = Dimension.DP)
    var textInputPaddingEndDp = DEFAULT_TEXT_INPUT_PADDING_DP
    @Dimension(unit = Dimension.DP)
    var textInputPaddingBottomDp = DEFAULT_TEXT_INPUT_PADDING_DP

    @Dimension(unit = Dimension.DP)
    var sendButtonWidthDp: Int = DEFAULT_SEND_BUTTON_SIZE_DP
    @Dimension(unit = Dimension.DP)
    var sendButtonHeightDp: Int = DEFAULT_SEND_BUTTON_SIZE_DP

    @Dimension(unit = Dimension.DP)
    var sendButtonMarginTopDp: Int = DEFAULT_SEND_BUTTON_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var sendButtonMarginStartDp: Int = DEFAULT_SEND_BUTTON_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var sendButtonMarginEndDp: Int = DEFAULT_SEND_BUTTON_MARGIN_END_DP
    @Dimension(unit = Dimension.DP)
    var sendButtonMarginBottomDp: Int = DEFAULT_SEND_BUTTON_MARGIN_DP
}