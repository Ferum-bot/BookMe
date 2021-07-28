package com.levit.book_me.ui.chat_kit

import android.content.Context
import com.levit.book_me.R
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions

fun provideDefaultInterlocutorStyleOptions(context: Context) = MessageStyleOptions {
    messageBackgroundColor = context.getColor(R.color.black)
    textColor = context.getColor(R.color.white)
    backgroundTextColor = context.getColor(R.color.black)
    textSizeSP = 14
    authorColor = context.getColor(R.color.white)
    authorLabelSizeSP = 16
    return@MessageStyleOptions this
}