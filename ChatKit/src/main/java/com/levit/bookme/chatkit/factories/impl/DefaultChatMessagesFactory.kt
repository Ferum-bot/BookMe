package com.levit.bookme.chatkit.factories.impl

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.levit.bookme.chatkit.models.chat_messages.MessageStyleOptions
import com.levit.bookme.chatkit.models.enums.MessageType
import com.levit.bookme.chatkit.models.chat_messages.MessageModel
import com.levit.bookme.chatkit.ui.chat_message.InterlocutorMessageView
import com.levit.bookme.chatkit.ui.chat_message.MessageView
import com.levit.bookme.chatkit.ui.chat_message.YourMessageView

internal class DefaultChatMessagesFactory(
    private val yourMessageOptions: MessageStyleOptions,
    private val interlocutorMessageOptions: MessageStyleOptions,
) {

    companion object {

        private const val MESSAGE_VIEW_WIDTH = ConstraintLayout.LayoutParams.MATCH_PARENT
        private const val MESSAGE_VIEW_HEIGHT = ConstraintLayout.LayoutParams.WRAP_CONTENT
    }

    fun createMessageFrom(
        position: Int, allMessages: List<MessageModel>, requireContext: () -> Context,
    ): MessageView {
        if (allMessages.notContainsPosition(position)) {
            throw IndexOutOfBoundsException("Invalid position to create view: $position. Size: ${allMessages.size}")
        }

        val modelToCreate = allMessages[position]
        return when(modelToCreate.type) {
            MessageType.YOUR_MESSAGE ->
                createYourMessage(
                    messageModel = modelToCreate,
                    previousModel = allMessages.getOrNull(position - 1),
                    requireContext,
                )
            MessageType.INTERLOCUTOR_MESSAGE ->
                createInterlocutorMessage(
                    messageModel = modelToCreate,
                    previousModel = allMessages.getOrNull(position - 1),
                    requireContext,
                )
        }
    }

    private fun createInterlocutorMessage(
        messageModel: MessageModel, previousModel: MessageModel?, requireContext: () -> Context
    ): InterlocutorMessageView {
        val iconIsNeeded: Boolean = previousModel == null || previousModel.type == MessageType.YOUR_MESSAGE
        return if (iconIsNeeded) {
            createInterlocutorMessageWithIcon(messageModel, requireContext)
        } else {
            createInterlocutorMessageWithoutIcon(messageModel, requireContext)
        }
    }

    private fun createYourMessage(
        messageModel: MessageModel, previousModel: MessageModel?, requireContext: () -> Context
    ): YourMessageView {
        val iconIsNeeded: Boolean = previousModel == null || previousModel.type == MessageType.INTERLOCUTOR_MESSAGE
        return if (iconIsNeeded) {
            createYourMessageWithIcon(messageModel, requireContext)
        } else {
            createYourMessageWithoutIcon(messageModel, requireContext)
        }
    }

    private fun createInterlocutorMessageWithIcon(
        messageModel: MessageModel, requireContext: () -> Context
    ): InterlocutorMessageView {
        val params = ConstraintLayout.LayoutParams(
            MESSAGE_VIEW_WIDTH,
            MESSAGE_VIEW_HEIGHT,
        )
        return InterlocutorMessageView(requireContext.invoke()).apply {
            this.layoutParams = params
            this.messageModel = messageModel
            this.isFirstMessage = true
            this.styleOptions = interlocutorMessageOptions
        }
    }

    private fun createInterlocutorMessageWithoutIcon(
        messageModel: MessageModel, requireContext: () -> Context
    ): InterlocutorMessageView {
        val params = ConstraintLayout.LayoutParams(
            MESSAGE_VIEW_WIDTH,
            MESSAGE_VIEW_HEIGHT,
        )
        return InterlocutorMessageView(requireContext.invoke()).apply {
            this.layoutParams = params
            this.messageModel = messageModel
            this.isFirstMessage = false
            this.styleOptions = interlocutorMessageOptions
        }
    }

    private fun createYourMessageWithIcon(
        messageModel: MessageModel, requireContext: () -> Context
    ): YourMessageView {
        val params = ConstraintLayout.LayoutParams(
            MESSAGE_VIEW_WIDTH,
            MESSAGE_VIEW_HEIGHT
        )
        return YourMessageView(requireContext.invoke()).apply {
            this.layoutParams = params
            this.messageModel = messageModel
            this.isFirstMessage = true
            this.styleOptions = yourMessageOptions
        }
    }

    private fun createYourMessageWithoutIcon(
        messageModel: MessageModel, requireContext: () -> Context
    ): YourMessageView {
        val params = ConstraintLayout.LayoutParams(
            MESSAGE_VIEW_WIDTH,
            MESSAGE_VIEW_HEIGHT,
        )
        return YourMessageView(requireContext.invoke()).apply {
            this.layoutParams = params
            this.messageModel = messageModel
            this.isFirstMessage = false
            this.styleOptions = yourMessageOptions
        }
    }

    private fun List<MessageModel>.notContainsPosition(position: Int): Boolean {
        if (position < 0) {
            return true
        }
        if (position >= size) {
            return true
        }
        return false
    }
}