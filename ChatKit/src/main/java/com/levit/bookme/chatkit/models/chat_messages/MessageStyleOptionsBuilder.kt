package com.levit.bookme.chatkit.models.chat_messages

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import com.levit.bookme.chatkit.models.enums.MessageDateFormat
import com.levit.bookme.chatkit.models.enums.MessageTextAlignment
import java.security.DigestException

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
    var backgroundTextColor: Int = Color.BLACK
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

    @Dimension(unit = Dimension.DP)
    var messageBackgroundTopLeftCornerRadiusDP: Int = DEFAULT_TOP_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var messageBackgroundTopRightCornerRadiusDP: Int = DEFAULT_TOP_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var messageBackgroundBottomLeftCornerRadiusDP: Int = DEFAULT_BOTTOM_RADIUS_DP
    @Dimension(unit = Dimension.DP)
    var messageBackgroundBottomRightCornerRadiusDP: Int = DEFAULT_BOTTOM_RADIUS_DP

    var authorLabelAlignment: MessageTextAlignment = MessageTextAlignment.START
    var dateLabelAlignment: MessageTextAlignment = MessageTextAlignment.END

    var dateShowFormat: MessageDateFormat = MessageDateFormat.SHOW_ONLY_TIME

    @Dimension(unit = Dimension.SP)
    var textSizeSP: Int = DEFAULT_BIG_TEXT_SIZE_SP
    @Dimension(unit = Dimension.SP)
    var authorLabelSizeSP: Int = DEFAULT_MEDIUM_TEXT_SIZE_SP
    @Dimension(unit = Dimension.SP)
    var dateLabelSizeSP: Int = DEFAULT_SMALL_TEXT_SIZE_SP

    @Dimension(unit = Dimension.DP)
    var textMarginTopDP: Int = DEFAULT_TEXT_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var textMarginStartDP: Int = DEFAULT_TEXT_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var textMarginEndDP: Int = DEFAULT_TEXT_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var textMarginBottom: Int = DEFAULT_TEXT_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var authorLabelMarginTop: Int = DEFAULT_AUTHOR_LABEL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var authorLabelMarginStart: Int = DEFAULT_AUTHOR_LABEL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var authorLabelMarginEnd: Int = DEFAULT_AUTHOR_LABEL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var authorLabelMarginBottom: Int = DEFAULT_AUTHOR_LABEL_MARGIN_DP

    @Dimension(unit = Dimension.DP)
    var dateLabelMarginTop: Int = DEFAULT_DATE_LABEL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var dateLabelMarginStart: Int = DEFAULT_DATE_LABEL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var dateLabelMarginEnd: Int = DEFAULT_DATE_LABEL_MARGIN_DP
    @Dimension(unit = Dimension.DP)
    var dateLabelMarginBottom: Int = DEFAULT_DATE_LABEL_MARGIN_DP

    var showAuthorLabel: Boolean = true
    var showDateLabel: Boolean = true
    var showProfileImage: Boolean = true
}
