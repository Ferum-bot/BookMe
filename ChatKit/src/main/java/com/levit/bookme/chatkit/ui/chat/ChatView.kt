package com.levit.bookme.chatkit.ui.chat

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.chat_kit.databinding.ChatLayoutBinding

class ChatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ChatLayoutBinding

    private val inflater: LayoutInflater
    get() = LayoutInflater.from(context)

    init {
        binding = ChatLayoutBinding.inflate(inflater, this, true)

    }

}