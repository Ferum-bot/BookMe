package com.levit.bookme.chatkit.models.general_chat

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.Dimension

class GeneralChatStyleOptionsBuilder {

    companion object {

        private const val DEFAULT_LAYOUT_EXTRA_SPACE_DP = 0
    }

    @ColorInt
    var layoutBackgroundColor: Int = Color.WHITE

    @Dimension(unit = Dimension.DP)
    var layoutTopExtraSpaceDp: Int = DEFAULT_LAYOUT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var layoutBottomExtraSpaceDp: Int = DEFAULT_LAYOUT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var layoutStartExtraSpaceDp: Int = DEFAULT_LAYOUT_EXTRA_SPACE_DP
    @Dimension(unit = Dimension.DP)
    var layoutEndExtraSpaceDp: Int = DEFAULT_LAYOUT_EXTRA_SPACE_DP

}