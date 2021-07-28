package com.levit.book_me.ui.chat_kit

import android.content.Context
import com.levit.book_me.R
import com.levit.bookme.chatkit.models.message_input.MessageInputStyleOptions

fun provideMessageInputStyleOptions(context: Context) = MessageInputStyleOptions {
    textInputBackgroundColor = context.getColor(R.color.white)
    textInputBackgroundStrokeColor = context.getColor(R.color.grey)
    textInputBackgroundStrokeWidthDp = 1

    return@MessageInputStyleOptions this
}