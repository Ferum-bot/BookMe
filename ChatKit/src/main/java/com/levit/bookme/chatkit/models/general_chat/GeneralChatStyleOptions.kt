package com.levit.bookme.chatkit.models.general_chat

import androidx.annotation.ColorInt
import androidx.annotation.Dimension

class GeneralChatStyleOptions(
    build: GeneralChatStyleOptionsBuilder.() -> GeneralChatStyleOptionsBuilder
) {

    companion object {

        fun provideDefaultStyleOptions(): GeneralChatStyleOptions {
            return GeneralChatStyleOptions {
                return@GeneralChatStyleOptions this
            }
        }
    }

    @ColorInt
    val layoutBackgroundColor: Int

    @Dimension(unit = Dimension.DP)
    val layoutTopExtraSpaceDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutBottomExtraSpaceDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutStartExtraSpaceDp: Int
    @Dimension(unit = Dimension.DP)
    val layoutEndExtraSpaceDp: Int

    init {
        val options = GeneralChatStyleOptionsBuilder().build()

        this.layoutBackgroundColor = options.layoutBackgroundColor

        this.layoutTopExtraSpaceDp = options.layoutTopExtraSpaceDp
        this.layoutBottomExtraSpaceDp = options.layoutBottomExtraSpaceDp
        this.layoutStartExtraSpaceDp = options.layoutStartExtraSpaceDp
        this.layoutEndExtraSpaceDp = options.layoutEndExtraSpaceDp
    }
}