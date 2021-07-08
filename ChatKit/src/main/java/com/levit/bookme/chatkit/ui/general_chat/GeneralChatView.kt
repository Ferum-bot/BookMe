package com.levit.bookme.chatkit.ui.general_chat

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.chat_kit.databinding.GeneralChatLayoutBinding
import com.levit.bookme.chatkit.extensions.inflater

@Suppress("JoinDeclarationAndAssignment")
class GeneralChatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: GeneralChatLayoutBinding

    init {
        binding = GeneralChatLayoutBinding.inflate(inflater, this, true)
    }

}