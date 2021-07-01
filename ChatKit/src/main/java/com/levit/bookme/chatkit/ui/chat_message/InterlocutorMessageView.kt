package com.levit.bookme.chatkit.ui.chat_message

import android.content.Context
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding
import com.levit.book_me.chat_kit.databinding.InterlocutorChatMessageLayoutBinding
import com.levit.bookme.chatkit.ui.chat_message.MessageView

class InterlocutorMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): MessageView(context, attrs, defStyleAttr) {

    override val binding: ViewBinding

    init {
        binding = InterlocutorChatMessageLayoutBinding.inflate(inflater, this, true)
    }
}