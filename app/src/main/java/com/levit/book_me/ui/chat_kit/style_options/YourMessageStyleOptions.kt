package com.levit.book_me.ui.chat_kit

import android.content.Context
import com.levit.book_me.R
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.enums.MessageDateFormat

fun provideDefaultYourStyleOptions(context: Context) = MessageStyleOptions {
    messageBackgroundColor = context.getColor(R.color.light_grey)
    backgroundTextColor = context.getColor(R.color.light_grey)
    dateShowFormat = MessageDateFormat.SHOW_ONLY_TIME
    dateColor = context.getColor(R.color.grey)
    textSizeSP = 14
    authorColor = context.getColor(R.color.black)
    authorLabelSizeSP = 16
    return@MessageStyleOptions this
}