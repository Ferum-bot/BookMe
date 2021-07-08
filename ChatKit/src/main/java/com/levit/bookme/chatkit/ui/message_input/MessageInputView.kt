package com.levit.bookme.chatkit.ui.message_input

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.chat_kit.databinding.MessageInputLayoutBinding
import com.levit.bookme.chatkit.extensions.inflater

@Suppress("JoinDeclarationAndAssignment")
class MessageInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: MessageInputLayoutBinding

    init {
        binding = MessageInputLayoutBinding.inflate(inflater, this, true)
    }

}