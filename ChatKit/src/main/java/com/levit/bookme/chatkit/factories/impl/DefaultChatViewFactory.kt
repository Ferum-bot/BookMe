package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.bookme.chatkit.models.chat.ChatStyleOptions
import com.levit.bookme.chatkit.models.chat.ChatModel
import com.levit.bookme.chatkit.ui.chat.ChatView

internal class DefaultChatViewFactory {

    fun createChatFrom(
        position: Int, allChats: List<ChatModel>, requireContext: () -> Context,
        styleOptions: ChatStyleOptions
    ): ChatView {
        if (allChats.notContainsPosition(position)) {
            throwInvalidPosition(position, allChats.size)
        }
        val modelToCreate = allChats[position]

        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
        )
        return ChatView(requireContext.invoke()).apply {
            this.styleOptions = styleOptions
            this.chatModel = modelToCreate
            this.layoutParams = layoutParams
        }
    }

    fun bindChatFrom(
        view: ChatView, position: Int, allChats: List<ChatModel>,
        styleOptions: ChatStyleOptions
    ) {
        if (allChats.notContainsPosition(position)) {
            throwInvalidPosition(position, allChats.size)
        }
        val modelToBind = allChats[position]
        view.chatModel = modelToBind
        view.styleOptions = styleOptions
    }

    private fun throwInvalidPosition(position: Int, size: Int): Nothing {
        throw IndexOutOfBoundsException("Invalid position to create chat: position: $position, size: $size")
    }

    private fun List<ChatModel>.notContainsPosition(position: Int): Boolean {
        return position < 0 || position >= size
    }
}