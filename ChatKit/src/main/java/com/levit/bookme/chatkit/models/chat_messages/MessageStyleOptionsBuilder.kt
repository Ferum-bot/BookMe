package com.levit.bookme.chatkit.models.chat_messages

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.MessageDateFormat
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment

class MessageStyleOptionsBuilder {

    companion object {

        private const val DEFAULT_BIG_TEXT_SIZE_SP = 20
        private const val DEFAULT_MEDIUM_TEXT_SIZE_SP = 15
        private const val DEFAULT_SMALL_TEXT_SIZE_SP = 10

        private const val DEFAULT_TEXT_MARGIN_DP = 6
        private const val DEFAULT_AUTHOR_LABEL_MARGIN_DP = 2
        private const val DEFAULT_DATE_LABEL_MARGIN_DP = 2

        private const val DEFAULT_TOP_RADIUS_DP = 15
        private const val DEFAULT_BOTTOM_RADIUS_DP = 10

        private const val DEFAULT_STROKE_WIDTH = 0
    }

    @ColorInt
    var textColor: Int = Color.BLACK
    @ColorInt
    var authorColor: Int = Color.GRAY
    @ColorInt
    var dateColor: Int = Color.LTGRAY

    @ColorInt
    var messageBackgroundColor: Int = Color.BLUE
    @ColorInt
    var messageBackgroundStrokeColor: Int = Color.BLUE

    @Dimension
    var messageBackgroundStrokeWidthDP: Int = DEFAULT_STROKE_WIDTH

    @Dimension
    var messageBackgroundTopLeftCornerRadiusDP: Int = DEFAULT_TOP_RADIUS_DP
    @Dimension
    var messageBackgroundTopRightCornerRadiusDP: Int = DEFAULT_TOP_RADIUS_DP
    @Dimension
    var messageBackgroundBottomLeftCornerRadiusDP: Int = DEFAULT_BOTTOM_RADIUS_DP
    @Dimension
    var messageBackgroundBottomRightCornerRadiusDP: Int = DEFAULT_BOTTOM_RADIUS_DP

    var authorLabelAlignment: MessageTextAlignment = MessageTextAlignment.START
    var dateLabelAlignment: MessageTextAlignment = MessageTextAlignment.END

    var dateShowFormat: MessageDateFormat = MessageDateFormat.SHOW_ONLY_TIME

    @Dimension
    var textSizeSP: Int = DEFAULT_BIG_TEXT_SIZE_SP
    @Dimension
    var authorLabelSizeSP: Int = DEFAULT_MEDIUM_TEXT_SIZE_SP
    @Dimension
    var dateLabelSizeSP: Int = DEFAULT_SMALL_TEXT_SIZE_SP

    @Dimension
    var textMarginTopDP: Int = DEFAULT_TEXT_MARGIN_DP
    @Dimension
    var textMarginStartDP: Int = DEFAULT_TEXT_MARGIN_DP
    @Dimension
    var textMarginEndDP: Int = DEFAULT_TEXT_MARGIN_DP
    @Dimension
    var textMarginBottom: Int = DEFAULT_TEXT_MARGIN_DP

    @Dimension
    var authorLabelMarginTop: Int = DEFAULT_AUTHOR_LABEL_MARGIN_DP
    @Dimension
    var authorLabelMarginStart: Int = DEFAULT_AUTHOR_LABEL_MARGIN_DP
    @Dimension
    var authorLabelMarginEnd: Int = DEFAULT_AUTHOR_LABEL_MARGIN_DP
    @Dimension
    var authorLabelMarginBottom: Int = DEFAULT_AUTHOR_LABEL_MARGIN_DP

    @Dimension
    var dateLabelMarginTop: Int = DEFAULT_DATE_LABEL_MARGIN_DP
    @Dimension
    var dateLabelMarginStart: Int = DEFAULT_DATE_LABEL_MARGIN_DP
    @Dimension
    var dateLabelMarginEnd: Int = DEFAULT_DATE_LABEL_MARGIN_DP
    @Dimension
    var dateLabelMarginBottom: Int = DEFAULT_DATE_LABEL_MARGIN_DP

    var showAuthorLabel: Boolean = true
    var showDateLabel: Boolean = true
    var showProfileImage: Boolean = true
}
