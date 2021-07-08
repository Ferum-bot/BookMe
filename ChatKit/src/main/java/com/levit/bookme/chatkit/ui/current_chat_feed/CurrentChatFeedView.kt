package com.levit.bookme.chatkit.ui.current_chat_feed

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.book_me.chat_kit.databinding.CurrentChatFeedLayoutBinding
import com.levit.bookme.chatkit.extensions.inflater

@Suppress("JoinDeclarationAndAssignment")
class CurrentChatFeedView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CurrentChatFeedLayoutBinding

    init {
        binding = CurrentChatFeedLayoutBinding.inflate(inflater, this, true)
    }

}