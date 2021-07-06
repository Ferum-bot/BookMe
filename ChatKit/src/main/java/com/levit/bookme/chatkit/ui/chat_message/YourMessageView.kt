package com.levit.bookme.chatkit.ui.chat_message

import android.content.Context
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding
import com.levit.book_me.chat_kit.databinding.YourChatMessageLayoutBinding
import com.levit.bookme.chatkit.models.MessageStyleOptions
import com.levit.bookme.chatkit.ui.chat_message.MessageView

class YourMessageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): MessageView(context, attrs, defStyleAttr) {

    override val binding: YourChatMessageLayoutBinding

    init {
        binding = YourChatMessageLayoutBinding.inflate(inflater, this, true)
    }

    override fun applyStyleOptions(options: MessageStyleOptions) {

    }
}